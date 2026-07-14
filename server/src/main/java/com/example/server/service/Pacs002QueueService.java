package com.example.server.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.example.server.model.pacs002.Agent;
import com.example.server.model.pacs002.AppHdr;
import com.example.server.model.pacs002.Body;
import com.example.server.model.pacs002.ClrSysMmbId;
import com.example.server.model.pacs002.DataPDU;
import com.example.server.model.pacs002.FIId;
import com.example.server.model.pacs002.FIToFIPmtStsRpt;
import com.example.server.model.pacs002.FinInstnId;
import com.example.server.model.pacs002.GroupHeader;
import com.example.server.model.pacs002.IntrBkSttlmAmt;
import com.example.server.model.pacs002.OriginalGroupInfoAndStatus;
import com.example.server.model.pacs002.OriginalTransactionReference;
import com.example.server.model.pacs002.Party;
import com.example.server.model.pacs002.PaymentTypeInformation;
import com.example.server.model.pacs002.ProprietaryValue;
import com.example.server.model.pacs002.TransactionInfoAndStatus;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class Pacs002QueueService {

    private final Queue<String> pendingMessages = new ConcurrentLinkedQueue<>();
    private final Map<String, String> messagesByTxId = new ConcurrentHashMap<>();

    public void enqueueAcceptedStatusForPacs008(String pacs008Xml) {
        Pacs008Reference reference = parsePacs008Reference(pacs008Xml);
        String pacs002Xml = buildAcceptedPacs002(reference);
        pendingMessages.add(pacs002Xml);
        messagesByTxId.put(safe(reference.txId), pacs002Xml);
    }

    public List<String> drainPendingMessages() {
        List<String> messages = new ArrayList<>();
        String message;
        while ((message = pendingMessages.poll()) != null) {
            messages.add(message);
        }
        return messages;
    }

    public Optional<String> findGeneratedMessageByTxId(String txId) {
        return Optional.ofNullable(messagesByTxId.get(txId));
    }

    private Pacs008Reference parsePacs008Reference(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Document document = factory
                    .newDocumentBuilder()
                    .parse(new InputSource(new StringReader(xml)));

            Pacs008Reference reference = new Pacs008Reference();
            reference.msgId = readFirstTag(document, "MsgId");
            reference.instrId = readFirstTag(document, "InstrId");
            reference.endToEndId = readFirstTag(document, "EndToEndId");
            reference.txId = readFirstTag(document, "TxId");
            reference.createdAt = readFirstTag(document, "CreDtTm");
            reference.amount = readFirstTag(document, "IntrBkSttlmAmt");
            reference.currency = readFirstAttribute(document, "IntrBkSttlmAmt", "Ccy");
            reference.settlementDate = readFirstTag(document, "IntrBkSttlmDt");
            reference.clearingChannel = readFirstTag(document, "ClrChanl");
            reference.serviceLevel = readNestedTag(document, "SvcLvl", "Prtry");
            reference.localInstrument = readNestedTag(document, "LclInstrm", "Prtry");
            reference.categoryPurpose = readNestedTag(document, "CtgyPurp", "Prtry");
            reference.purpose = readNestedTag(document, "Purp", "Prtry");
            return reference;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Unable to parse pacs.008 XML", ex);
        }
    }

    private String readFirstTag(Document document, String tagName) {
        NodeList nodes = findElements(document, tagName);
        return nodes.getLength() == 0 ? null : nodes.item(0).getTextContent();
    }

    private String readFirstAttribute(Document document, String tagName, String attributeName) {
        NodeList nodes = findElements(document, tagName);
        if (nodes.getLength() == 0 || !(nodes.item(0) instanceof Element element)) {
            return null;
        }
        return element.getAttribute(attributeName);
    }

    private String readNestedTag(Document document, String parentTagName, String childTagName) {
        NodeList parents = findElements(document, parentTagName);
        if (parents.getLength() == 0) {
            return null;
        }

        NodeList children = parents.item(0).getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE && childTagName.equals(child.getLocalName())) {
                return child.getTextContent();
            }
        }
        return null;
    }

    private NodeList findElements(Document document, String tagName) {
        NodeList nodes = document.getElementsByTagNameNS("*", tagName);
        return nodes.getLength() == 0 ? document.getElementsByTagName(tagName) : nodes;
    }

    private String buildAcceptedPacs002(Pacs008Reference reference) {
        try {
            return marshalXml(buildAcceptedPacs002Message(reference));
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to build pacs.002 XML", ex);
        }
    }

    private DataPDU buildAcceptedPacs002Message(Pacs008Reference reference) {
        String now = OffsetDateTime.now().toString();
        String statusMsgId = "STS-" + safe(reference.txId);

        DataPDU dataPdu = new DataPDU();
        Body body = new Body();
        com.example.server.model.pacs002.Document document = new com.example.server.model.pacs002.Document();
        FIToFIPmtStsRpt report = new FIToFIPmtStsRpt();

        body.setAppHdr(buildAppHdr(statusMsgId, now));
        report.setGrpHdr(buildGroupHeader(statusMsgId, now));
        report.setOrgnlGrpInfAndSts(buildOriginalGroupInfo(reference));
        report.setTxInfAndSts(buildTransactionInfo(statusMsgId, reference));

        document.setFiToFiPmtStsRpt(report);
        body.setDocument(document);
        dataPdu.setBody(body);
        return dataPdu;
    }

    private AppHdr buildAppHdr(String statusMsgId, String createdAt) {
        AppHdr appHdr = new AppHdr();
        appHdr.setFr(buildParty("SBPPPKKAIPS"));
        appHdr.setTo(buildParty("NAYAPKKA"));
        appHdr.setBizMsgIdr(statusMsgId);
        appHdr.setMsgDefIdr("pacs.002.001.10");
        appHdr.setBizSvc("IPS");
        appHdr.setCreDt(createdAt);
        return appHdr;
    }

    private GroupHeader buildGroupHeader(String statusMsgId, String createdAt) {
        GroupHeader groupHeader = new GroupHeader();
        groupHeader.setMsgId(statusMsgId);
        groupHeader.setCreDtTm(createdAt);
        return groupHeader;
    }

    private OriginalGroupInfoAndStatus buildOriginalGroupInfo(Pacs008Reference reference) {
        OriginalGroupInfoAndStatus original = new OriginalGroupInfoAndStatus();
        original.setOrgnlMsgId(safe(reference.msgId));
        original.setOrgnlMsgNmId("pacs.008.001.08");
        original.setOrgnlCreDtTm(safe(reference.createdAt));
        original.setOrgnlNbOfTxs("1");
        original.setGrpSts("ACSP");
        return original;
    }

    private TransactionInfoAndStatus buildTransactionInfo(String statusMsgId, Pacs008Reference reference) {
        TransactionInfoAndStatus transaction = new TransactionInfoAndStatus();
        transaction.setStsId(statusMsgId);
        transaction.setOrgnlInstrId(safe(reference.instrId));
        transaction.setOrgnlEndToEndId(safe(reference.endToEndId));
        transaction.setOrgnlTxId(safe(reference.txId));
        transaction.setTxSts("ACSP");
        transaction.setAcctSvcrRef("REF-" + safe(reference.txId));
        transaction.setInstgAgt(buildAgent("SBPPPKKAIPS"));
        transaction.setInstdAgt(buildAgent("NAYAPKKA"));
        transaction.setOrgnlTxRef(buildOriginalTransactionReference(reference));
        return transaction;
    }

    private OriginalTransactionReference buildOriginalTransactionReference(Pacs008Reference reference) {
        OriginalTransactionReference originalReference = new OriginalTransactionReference();
        originalReference.setIntrBkSttlmAmt(buildAmount(reference.amount, reference.currency));
        originalReference.setIntrBkSttlmDt(safe(reference.settlementDate));
        originalReference.setPmtTpInf(buildPaymentTypeInformation(reference));
        originalReference.setPurp(buildProprietaryValue(safe(reference.purpose)));
        return originalReference;
    }

    private PaymentTypeInformation buildPaymentTypeInformation(Pacs008Reference reference) {
        PaymentTypeInformation paymentTypeInformation = new PaymentTypeInformation();
        paymentTypeInformation.setClrChanl(safe(reference.clearingChannel));
        paymentTypeInformation.setSvcLvl(buildProprietaryValue(safe(reference.serviceLevel)));
        paymentTypeInformation.setLclInstrm(buildProprietaryValue(safe(reference.localInstrument)));
        paymentTypeInformation.setCtgyPurp(buildProprietaryValue(safe(reference.categoryPurpose)));
        return paymentTypeInformation;
    }

    private IntrBkSttlmAmt buildAmount(String value, String currency) {
        IntrBkSttlmAmt amount = new IntrBkSttlmAmt();
        amount.setValue(safe(value));
        amount.setCcy(safe(currency));
        return amount;
    }

    private ProprietaryValue buildProprietaryValue(String value) {
        ProprietaryValue proprietaryValue = new ProprietaryValue();
        proprietaryValue.setPrtry(value);
        return proprietaryValue;
    }

    private Party buildParty(String memberId) {
        Party party = new Party();
        FIId fiId = new FIId();
        fiId.setFinInstnId(buildFinInstnId(memberId));
        party.setFiId(fiId);
        return party;
    }

    private Agent buildAgent(String memberId) {
        Agent agent = new Agent();
        agent.setFinInstnId(buildFinInstnId(memberId));
        return agent;
    }

    private FinInstnId buildFinInstnId(String memberId) {
        FinInstnId finInstnId = new FinInstnId();
        ClrSysMmbId clrSysMmbId = new ClrSysMmbId();
        clrSysMmbId.setMmbId(memberId);
        finInstnId.setClrSysMmbId(clrSysMmbId);
        return finInstnId;
    }

    private String marshalXml(DataPDU dataPdu) throws Exception {
        JAXBContext context = JAXBContext.newInstance(DataPDU.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(dataPdu, writer);
        return writer.toString();
    }

    private String safe(String value) {
        return value == null || value.isBlank() ? "UNKNOWN" : value;
    }

    private static class Pacs008Reference {
        private String msgId;
        private String instrId;
        private String endToEndId;
        private String txId;
        private String createdAt;
        private String amount;
        private String currency;
        private String settlementDate;
        private String clearingChannel;
        private String serviceLevel;
        private String localInstrument;
        private String categoryPurpose;
        private String purpose;
    }
}
