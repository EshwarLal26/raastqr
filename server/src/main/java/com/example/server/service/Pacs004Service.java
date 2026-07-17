package com.example.server.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.example.server.model.pacs004.AccountId;
import com.example.server.model.pacs004.Agent;
import com.example.server.model.pacs004.AppHdr;
import com.example.server.model.pacs004.Body;
import com.example.server.model.pacs004.CashAccount;
import com.example.server.model.pacs004.ClrSysMmbId;
import com.example.server.model.pacs004.CurrencyAmount;
import com.example.server.model.pacs004.DataPDU;
import com.example.server.model.pacs004.FIId;
import com.example.server.model.pacs004.FIToFIPmtRtr;
import com.example.server.model.pacs004.FinInstnId;
import com.example.server.model.pacs004.GroupHeader;
import com.example.server.model.pacs004.NameOnlyParty;
import com.example.server.model.pacs004.OriginalGroupInformation;
import com.example.server.model.pacs004.Party;
import com.example.server.model.pacs004.ReturnReason;
import com.example.server.model.pacs004.ReturnReasonInformation;
import com.example.server.model.pacs004.SettlementInformation;
import com.example.server.model.pacs004.TransactionInformation;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class Pacs004Service {

    public String buildPacs004FromPacs008(String pacs008Xml, String reasonCode, String additionalInfo) {
        try {
            Pacs008Reference reference = parsePacs008Reference(pacs008Xml);
            return marshalXml(buildPacs004Message(reference, reasonCode, additionalInfo));
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to build pacs.004 XML", ex);
        }
    }

    private Pacs008Reference parsePacs008Reference(String xml) throws Exception {
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
        reference.amount = readFirstTag(document, "IntrBkSttlmAmt");
        reference.currency = readFirstAttribute(document, "IntrBkSttlmAmt", "Ccy");
        reference.settlementDate = readFirstTag(document, "IntrBkSttlmDt");
        reference.chargeBearer = readFirstTag(document, "ChrgBr");
        reference.debtorName = readNestedText(document, "Dbtr", "Nm");
        reference.creditorName = readNestedText(document, "Cdtr", "Nm");
        reference.debtorIban = readNestedText(document, "DbtrAcct", "IBAN");
        reference.creditorIban = readNestedText(document, "CdtrAcct", "IBAN");
        reference.instgAgent = readNestedText(document, "InstgAgt", "MmbId");
        reference.instdAgent = readNestedText(document, "InstdAgt", "MmbId");
        reference.debtorAgent = readNestedText(document, "DbtrAgt", "MmbId");
        reference.creditorAgent = readNestedText(document, "CdtrAgt", "MmbId");
        return reference;
    }

    private DataPDU buildPacs004Message(Pacs008Reference reference, String reasonCode, String additionalInfo) {
        String returnId = "RTR-" + safe(reference.txId);
        String createdAt = OffsetDateTime.now().toString();

        DataPDU dataPdu = new DataPDU();
        Body body = new Body();
        com.example.server.model.pacs004.Document document = new com.example.server.model.pacs004.Document();
        FIToFIPmtRtr paymentReturn = new FIToFIPmtRtr();

        body.setAppHdr(buildAppHdr(returnId, createdAt));
        paymentReturn.setGrpHdr(buildGroupHeader(returnId, createdAt));
        paymentReturn.setTxInf(buildTransactionInformation(reference, returnId, reasonCode, additionalInfo));
        document.setFiToFiPmtRtr(paymentReturn);
        body.setDocument(document);
        dataPdu.setBody(body);
        return dataPdu;
    }

    private AppHdr buildAppHdr(String returnId, String createdAt) {
        AppHdr appHdr = new AppHdr();
        appHdr.setFr(buildParty("SBPPPKKAIPS"));
        appHdr.setTo(buildParty("NAYAPKKA"));
        appHdr.setBizMsgIdr(returnId);
        appHdr.setMsgDefIdr("pacs.004.001.09");
        appHdr.setBizSvc("IPS");
        appHdr.setCreDt(createdAt);
        return appHdr;
    }

    private GroupHeader buildGroupHeader(String returnId, String createdAt) {
        GroupHeader groupHeader = new GroupHeader();
        groupHeader.setMsgId(returnId);
        groupHeader.setCreDtTm(createdAt);
        groupHeader.setNbOfTxs("1");
        SettlementInformation settlementInformation = new SettlementInformation();
        settlementInformation.setSttlmMtd("CLRG");
        groupHeader.setSttlmInf(settlementInformation);
        return groupHeader;
    }

    private TransactionInformation buildTransactionInformation(Pacs008Reference reference,
                                                              String returnId,
                                                              String reasonCode,
                                                              String additionalInfo) {
        TransactionInformation txInf = new TransactionInformation();
        txInf.setRtrId(returnId);
        txInf.setOrgnlGrpInf(buildOriginalGroupInformation(reference));
        txInf.setOrgnlInstrId(safe(reference.instrId));
        txInf.setOrgnlEndToEndId(safe(reference.endToEndId));
        txInf.setOrgnlTxId(safe(reference.txId));
        txInf.setOrgnlIntrBkSttlmAmt(buildAmount(reference.amount, reference.currency));
        txInf.setRtrdIntrBkSttlmAmt(buildAmount(reference.amount, reference.currency));
        txInf.setIntrBkSttlmDt(safeDate(reference.settlementDate));
        txInf.setChrgBr(safeOr(reference.chargeBearer, "DEBT"));
        txInf.setRtrRsnInf(buildReturnReasonInformation(reasonCode, additionalInfo));
        txInf.setInstgAgt(buildAgent(safeOr(reference.instdAgent, "SBPPPKKAIPS")));
        txInf.setInstdAgt(buildAgent(safeOr(reference.instgAgent, "NAYAPKKA")));
        txInf.setDbtr(buildNameOnlyParty(safeOr(reference.creditorName, "UNKNOWN_CREDITOR")));
        txInf.setDbtrAcct(buildCashAccount(safeOr(reference.creditorIban, "UNKNOWN")));
        txInf.setDbtrAgt(buildAgent(safeOr(reference.creditorAgent, "UNKNOWN")));
        txInf.setCdtrAgt(buildAgent(safeOr(reference.debtorAgent, "UNKNOWN")));
        txInf.setCdtr(buildNameOnlyParty(safeOr(reference.debtorName, "UNKNOWN_DEBTOR")));
        txInf.setCdtrAcct(buildCashAccount(safeOr(reference.debtorIban, "UNKNOWN")));
        return txInf;
    }

    private OriginalGroupInformation buildOriginalGroupInformation(Pacs008Reference reference) {
        OriginalGroupInformation originalGroupInformation = new OriginalGroupInformation();
        originalGroupInformation.setOrgnlMsgId(safe(reference.msgId));
        originalGroupInformation.setOrgnlMsgNmId("pacs.008.001.08");
        return originalGroupInformation;
    }

    private CurrencyAmount buildAmount(String value, String currency) {
        CurrencyAmount amount = new CurrencyAmount();
        amount.setValue(new BigDecimal(safeNumber(value)));
        amount.setCcy(safeOr(currency, "PKR"));
        return amount;
    }

    private ReturnReasonInformation buildReturnReasonInformation(String reasonCode, String additionalInfo) {
        ReturnReasonInformation info = new ReturnReasonInformation();
        info.setOrgtr(buildNameOnlyParty("SBPPPKKAIPS"));
        ReturnReason reason = new ReturnReason();
        reason.setCd(safeOr(reasonCode, "AC04"));
        info.setRsn(reason);
        info.setAddtlInf(safeOr(additionalInfo, "Payment returned by bank."));
        return info;
    }

    private Party buildParty(String memberId) {
        Party party = new Party();
        FIId fiId = new FIId();
        fiId.setFinInstnId(buildFinInstnId(memberId));
        party.setFIId(fiId);
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

    private NameOnlyParty buildNameOnlyParty(String name) {
        NameOnlyParty party = new NameOnlyParty();
        party.setNm(name);
        return party;
    }

    private CashAccount buildCashAccount(String iban) {
        CashAccount cashAccount = new CashAccount();
        AccountId accountId = new AccountId();
        accountId.setIban(iban);
        cashAccount.setId(accountId);
        return cashAccount;
    }

    private String marshalXml(DataPDU dataPdu) throws Exception {
        JAXBContext context = JAXBContext.newInstance(DataPDU.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(dataPdu, writer);
        return writer.toString();
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

    private String readNestedText(Document document, String parentTagName, String childTagName) {
        NodeList parents = findElements(document, parentTagName);
        if (parents.getLength() == 0) {
            return null;
        }
        return findDescendantText(parents.item(0), childTagName);
    }

    private String findDescendantText(Node node, String tagName) {
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE && tagName.equals(child.getLocalName())) {
                return child.getTextContent();
            }
            String descendantValue = findDescendantText(child, tagName);
            if (descendantValue != null) {
                return descendantValue;
            }
        }
        return null;
    }

    private NodeList findElements(Document document, String tagName) {
        NodeList nodes = document.getElementsByTagNameNS("*", tagName);
        return nodes.getLength() == 0 ? document.getElementsByTagName(tagName) : nodes;
    }

    private String safe(String value) {
        return value == null || value.isBlank() ? "UNKNOWN" : value;
    }

    private String safeOr(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    private String safeDate(String value) {
        return value == null || value.isBlank() ? LocalDate.now().toString() : value;
    }

    private String safeNumber(String value) {
        return value == null || value.isBlank() ? "0" : value;
    }

    private static class Pacs008Reference {
        private String msgId;
        private String instrId;
        private String endToEndId;
        private String txId;
        private String amount;
        private String currency;
        private String settlementDate;
        private String chargeBearer;
        private String debtorName;
        private String debtorIban;
        private String debtorAgent;
        private String creditorName;
        private String creditorIban;
        private String creditorAgent;
        private String instgAgent;
        private String instdAgent;
    }
}
