package com.example.raastqr.service;

import java.io.StringWriter;

import org.springframework.stereotype.Service;

import com.example.raastqr.dto.Pacs004Dto;
import com.example.raastqr.model.pacs004.AccountId;
import com.example.raastqr.model.pacs004.Agent;
import com.example.raastqr.model.pacs004.AppHdr;
import com.example.raastqr.model.pacs004.Body;
import com.example.raastqr.model.pacs004.CashAccount;
import com.example.raastqr.model.pacs004.ClrSysMmbId;
import com.example.raastqr.model.pacs004.CurrencyAmount;
import com.example.raastqr.model.pacs004.DataPDU;
import com.example.raastqr.model.pacs004.Document;
import com.example.raastqr.model.pacs004.FIId;
import com.example.raastqr.model.pacs004.FIToFIPmtRtr;
import com.example.raastqr.model.pacs004.FinInstnId;
import com.example.raastqr.model.pacs004.GroupHeader;
import com.example.raastqr.model.pacs004.NameOnlyParty;
import com.example.raastqr.model.pacs004.OriginalGroupInformation;
import com.example.raastqr.model.pacs004.Party;
import com.example.raastqr.model.pacs004.ReturnReason;
import com.example.raastqr.model.pacs004.ReturnReasonInformation;
import com.example.raastqr.model.pacs004.SettlementInformation;
import com.example.raastqr.model.pacs004.TransactionInformation;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class Pacs004Service {

    public String buildPacs004Xml(Pacs004Dto dto) throws Exception {
        DataPDU dataPdu = new DataPDU();
        Body body = new Body();
        Document document = new Document();
        FIToFIPmtRtr paymentReturn = new FIToFIPmtRtr();

        body.setAppHdr(buildAppHdr(dto));
        paymentReturn.setGrpHdr(buildGroupHeader(dto));
        paymentReturn.setTxInf(buildTransactionInformation(dto));
        document.setFiToFiPmtRtr(paymentReturn);
        body.setDocument(document);
        dataPdu.setBody(body);

        return marshalXml(dataPdu);
    }

    private AppHdr buildAppHdr(Pacs004Dto dto) {
        AppHdr appHdr = new AppHdr();
        appHdr.setFr(buildParty(dto.getFromMemberId()));
        appHdr.setTo(buildParty(dto.getToMemberId()));
        appHdr.setBizMsgIdr(dto.getBizMsgIdr());
        appHdr.setMsgDefIdr(dto.getMsgDefIdr());
        appHdr.setBizSvc(dto.getBizSvc());
        appHdr.setCreDt(dto.getAppHdrCreDt());
        return appHdr;
    }

    private Party buildParty(String memberId) {
        Party party = new Party();
        FIId fiId = new FIId();
        fiId.setFinInstnId(buildFinInstnId(memberId));
        party.setFIId(fiId);
        return party;
    }

    private GroupHeader buildGroupHeader(Pacs004Dto dto) {
        GroupHeader groupHeader = new GroupHeader();
        groupHeader.setMsgId(dto.getGrpHdrMsgId());
        groupHeader.setCreDtTm(dto.getGrpHdrCreDtTm());
        groupHeader.setNbOfTxs(dto.getNbOfTxs());
        groupHeader.setSttlmInf(buildSettlementInformation(dto));
        return groupHeader;
    }

    private SettlementInformation buildSettlementInformation(Pacs004Dto dto) {
        SettlementInformation settlementInformation = new SettlementInformation();
        settlementInformation.setSttlmMtd(dto.getSttlmMtd());
        return settlementInformation;
    }

    private TransactionInformation buildTransactionInformation(Pacs004Dto dto) {
        TransactionInformation txInf = new TransactionInformation();

        txInf.setRtrId(dto.getRtrId());
        txInf.setOrgnlGrpInf(buildOriginalGroupInformation(dto));
        txInf.setOrgnlInstrId(dto.getOrgnlInstrId());
        txInf.setOrgnlEndToEndId(dto.getOrgnlEndToEndId());
        txInf.setOrgnlTxId(dto.getOrgnlTxId());
        txInf.setOrgnlIntrBkSttlmAmt(buildAmount(
                dto.getOrgnlIntrBkSttlmAmt(),
                dto.getOrgnlIntrBkSttlmAmtCcy()
        ));
        txInf.setRtrdIntrBkSttlmAmt(buildAmount(
                dto.getRtrdIntrBkSttlmAmt(),
                dto.getRtrdIntrBkSttlmAmtCcy()
        ));
        txInf.setIntrBkSttlmDt(dto.getIntrBkSttlmDt());
        txInf.setChrgBr(dto.getChrgBr());
        txInf.setRtrRsnInf(buildReturnReasonInformation(dto));
        txInf.setInstgAgt(buildAgent(dto.getInstgAgtMmbId()));
        txInf.setInstdAgt(buildAgent(dto.getInstdAgtMmbId()));
        txInf.setDbtr(buildNameOnlyParty(dto.getDbtrName()));
        txInf.setDbtrAcct(buildCashAccount(dto.getDbtrIban()));
        txInf.setDbtrAgt(buildAgent(dto.getDbtrAgtMmbId()));
        txInf.setCdtrAgt(buildAgent(dto.getCdtrAgtMmbId()));
        txInf.setCdtr(buildNameOnlyParty(dto.getCdtrName()));
        txInf.setCdtrAcct(buildCashAccount(dto.getCdtrIban()));

        return txInf;
    }

    private OriginalGroupInformation buildOriginalGroupInformation(Pacs004Dto dto) {
        OriginalGroupInformation originalGroupInformation = new OriginalGroupInformation();
        originalGroupInformation.setOrgnlMsgId(dto.getOrgnlMsgId());
        originalGroupInformation.setOrgnlMsgNmId(dto.getOrgnlMsgNmId());
        return originalGroupInformation;
    }

    private CurrencyAmount buildAmount(java.math.BigDecimal value, String currency) {
        CurrencyAmount amount = new CurrencyAmount();
        amount.setValue(value);
        amount.setCcy(currency);
        return amount;
    }

    private ReturnReasonInformation buildReturnReasonInformation(Pacs004Dto dto) {
        ReturnReasonInformation info = new ReturnReasonInformation();
        info.setOrgtr(buildNameOnlyParty(dto.getReturnReasonOriginatorName()));
        info.setRsn(buildReturnReason(dto.getReturnReasonCode()));
        info.setAddtlInf(dto.getReturnAdditionalInfo());
        return info;
    }

    private ReturnReason buildReturnReason(String code) {
        ReturnReason reason = new ReturnReason();
        reason.setCd(code);
        return reason;
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
}