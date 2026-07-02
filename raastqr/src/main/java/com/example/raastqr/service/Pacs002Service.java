package com.example.raastqr.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.example.raastqr.dto.Pacs002Dto;
import com.example.raastqr.model.pacs002.Agent;
import com.example.raastqr.model.pacs002.AppHdr;
import com.example.raastqr.model.pacs002.Body;
import com.example.raastqr.model.pacs002.ClrSysMmbId;
import com.example.raastqr.model.pacs002.DataPDU;
import com.example.raastqr.model.pacs002.Document;
import com.example.raastqr.model.pacs002.FIId;
import com.example.raastqr.model.pacs002.FIToFIPmtStsRpt;
import com.example.raastqr.model.pacs002.FinInstnId;
import com.example.raastqr.model.pacs002.GroupHeader;
import com.example.raastqr.model.pacs002.IntrBkSttlmAmt;
import com.example.raastqr.model.pacs002.OriginalGroupInfoAndStatus;
import com.example.raastqr.model.pacs002.OriginalTransactionReference;
import com.example.raastqr.model.pacs002.Party;
import com.example.raastqr.model.pacs002.PaymentTypeInformation;
import com.example.raastqr.model.pacs002.ProprietaryValue;
import com.example.raastqr.model.pacs002.SignatureWrapper;
import com.example.raastqr.model.pacs002.TransactionInfoAndStatus;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class Pacs002Service {

    public String buildPacs002Xml(Pacs002Dto dto) throws Exception {
        DataPDU dataPdu = new DataPDU();
        Body body = new Body();
        Document document = new Document();
        FIToFIPmtStsRpt report = new FIToFIPmtStsRpt();

        body.setAppHdr(buildAppHdr(dto));
        report.setGrpHdr(buildGroupHeader(dto));
        report.setOrgnlGrpInfAndSts(buildOriginalGroupInfo(dto));
        report.setTxInfAndSts(buildTransactionInfo(dto));

        document.setFiToFiPmtStsRpt(report);
        body.setDocument(document);
        dataPdu.setBody(body);

        return marshalXml(dataPdu);
    }

    private AppHdr buildAppHdr(Pacs002Dto dto) throws Exception {
        AppHdr appHdr = new AppHdr();
        appHdr.setFr(buildParty(dto.getFromMemberId()));
        appHdr.setTo(buildParty(dto.getToMemberId()));
        appHdr.setBizMsgIdr(dto.getBizMsgIdr());
        appHdr.setMsgDefIdr(dto.getMsgDefIdr());
        appHdr.setBizSvc(dto.getBizSvc());
        appHdr.setCreDt(dto.getAppHdrCreDt());

        if (dto.getSignatureXml() != null && !dto.getSignatureXml().isBlank()) {
            SignatureWrapper wrapper = new SignatureWrapper();
            wrapper.setSignature(parseSignatureElement(dto.getSignatureXml()));
            appHdr.setSgntr(wrapper);
        }

        return appHdr;
    }

    private Party buildParty(String memberId) {
        Party party = new Party();
        FIId fiId = new FIId();
        fiId.setFinInstnId(buildFinInstnId(memberId));
        party.setFiId(fiId);
        return party;
    }

    private FinInstnId buildFinInstnId(String memberId) {
        FinInstnId finInstnId = new FinInstnId();
        ClrSysMmbId clrSysMmbId = new ClrSysMmbId();
        clrSysMmbId.setMmbId(memberId);
        finInstnId.setClrSysMmbId(clrSysMmbId);
        return finInstnId;
    }

    private GroupHeader buildGroupHeader(Pacs002Dto dto) {
        GroupHeader grpHdr = new GroupHeader();
        grpHdr.setMsgId(dto.getGrpHdrMsgId());
        grpHdr.setCreDtTm(dto.getGrpHdrCreDtTm());
        return grpHdr;
    }

    private OriginalGroupInfoAndStatus buildOriginalGroupInfo(Pacs002Dto dto) {
        OriginalGroupInfoAndStatus original = new OriginalGroupInfoAndStatus();
        original.setOrgnlMsgId(dto.getOrgnlMsgId());
        original.setOrgnlMsgNmId(dto.getOrgnlMsgNmId());
        original.setOrgnlCreDtTm(dto.getOrgnlCreDtTm());
        original.setOrgnlNbOfTxs(dto.getOrgnlNbOfTxs());
        original.setGrpSts(dto.getGrpSts());
        return original;
    }

    private TransactionInfoAndStatus buildTransactionInfo(Pacs002Dto dto) {
        TransactionInfoAndStatus tx = new TransactionInfoAndStatus();
        tx.setStsId(dto.getStsId());
        tx.setOrgnlInstrId(dto.getOrgnlInstrId());
        tx.setOrgnlEndToEndId(dto.getOrgnlEndToEndId());
        tx.setOrgnlTxId(dto.getOrgnlTxId());
        tx.setTxSts(dto.getTxSts());
        tx.setAcctSvcrRef(dto.getAcctSvcrRef());
        tx.setInstgAgt(buildAgent(dto.getInstgAgtMmbId()));
        tx.setInstdAgt(buildAgent(dto.getInstdAgtMmbId()));
        tx.setOrgnlTxRef(buildOriginalTransactionReference(dto));
        return tx;
    }

    private Agent buildAgent(String memberId) {
        Agent agent = new Agent();
        agent.setFinInstnId(buildFinInstnId(memberId));
        return agent;
    }

    private OriginalTransactionReference buildOriginalTransactionReference(Pacs002Dto dto) {
        OriginalTransactionReference ref = new OriginalTransactionReference();
        ref.setIntrBkSttlmAmt(buildIntrBkSttlmAmt(dto.getIntrBkSttlmAmt(), dto.getIntrBkSttlmAmtCcy()));
        ref.setIntrBkSttlmDt(dto.getIntrBkSttlmDt());
        ref.setPmtTpInf(buildPaymentTypeInformation(dto));
        ref.setPurp(buildProprietaryValue(dto.getPurpPrtry()));
        return ref;
    }

    private IntrBkSttlmAmt buildIntrBkSttlmAmt(BigDecimal amount, String ccy) {
        IntrBkSttlmAmt amt = new IntrBkSttlmAmt();
        amt.setCcy(ccy);
        amt.setValue(amount != null ? amount.toPlainString() : null);
        return amt;
    }

    private PaymentTypeInformation buildPaymentTypeInformation(Pacs002Dto dto) {
        PaymentTypeInformation pmtTpInf = new PaymentTypeInformation();
        pmtTpInf.setClrChanl(dto.getClrChanl());
        pmtTpInf.setSvcLvl(buildProprietaryValue(dto.getSvcLvlPrtry()));
        pmtTpInf.setLclInstrm(buildProprietaryValue(dto.getLclInstrmPrtry()));
        pmtTpInf.setCtgyPurp(buildProprietaryValue(dto.getCtgyPurpPrtry()));
        return pmtTpInf;
    }

    private ProprietaryValue buildProprietaryValue(String value) {
        ProprietaryValue proprietaryValue = new ProprietaryValue();
        proprietaryValue.setPrtry(value);
        return proprietaryValue;
    }

    private Element parseSignatureElement(String signatureXml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(signatureXml)))
                .getDocumentElement();
    }

    private String marshalXml(DataPDU dataPdu) throws Exception {
        JAXBContext context = JAXBContext.newInstance(DataPDU.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(dataPdu, writer);
        return writer.toString();
    }
}