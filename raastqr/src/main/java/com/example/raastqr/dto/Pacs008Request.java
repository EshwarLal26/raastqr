package com.example.raastqr.dto;

import java.math.BigDecimal;
import java.util.List;

public class Pacs008Request {

    private String submissionToken;

    private String fromMemberId;
    private String toMemberId;
    private String bizMsgIdr;
    private String msgDefIdr;
    private String bizSvc;
    private String appHdrCreDt;

    private String msgId;
    private String grpHdrCreDtTm;
    private boolean btchBookg;
    private String nbOfTxs;
    private String sttlmMtd;
    private String clrSys;

    private String instrId;
    private String endToEndId;
    private String txId;
    private String svcLvl;
    private String lclInstrm;
    private String ctgyPurp;
    private String clrChanl;
    private String instrPrty;

    private BigDecimal intrBkSttlmAmt;
    private String intrBkSttlmAmtCcy;
    private String intrBkSttlmDt;

    private BigDecimal instdAmt;
    private String instdAmtCcy;
    private String chrgBr;

    private String instgAgtMmbId;
    private String instgAgtBic;
    private String instdAgtMmbId;
    private String instdAgtBic;

    private String debtorName;
    private String debtorMobile;
    private String debtorEmail;
    private List<String> debtorChannelTypes;
    private String debtorIban;
    private String debtorAgentMmbId;
    private String debtorAgentBic;

    private String creditorAgentMmbId;
    private String creditorAgentBic;
    private String creditorName;
    private String creditorSubDept;
    private String creditorTownName;
    private String creditorMcc;
    private String creditorCountryOfRes;
    private String creditorContactName;
    private String creditorMobile;
    private String creditorDepartment;
    private String creditorIban;

    private String instrInf;
    private String purp;
    private String regulatoryReportingCode;
    private String regulatoryReportingInfo;

    private String referredDocType;
    private String referredDocRelatedDate;
    private BigDecimal duePayableAmt;
    private String duePayableAmtCcy;
    private String ustrd;

    public String getSubmissionToken() { return submissionToken; }
    public void setSubmissionToken(String submissionToken) { this.submissionToken = submissionToken; }

    public String getFromMemberId() { return fromMemberId; }
    public void setFromMemberId(String fromMemberId) { this.fromMemberId = fromMemberId; }

    public String getToMemberId() { return toMemberId; }
    public void setToMemberId(String toMemberId) { this.toMemberId = toMemberId; }

    public String getBizMsgIdr() { return bizMsgIdr; }
    public void setBizMsgIdr(String bizMsgIdr) { this.bizMsgIdr = bizMsgIdr; }

    public String getMsgDefIdr() { return msgDefIdr; }
    public void setMsgDefIdr(String msgDefIdr) { this.msgDefIdr = msgDefIdr; }

    public String getBizSvc() { return bizSvc; }
    public void setBizSvc(String bizSvc) { this.bizSvc = bizSvc; }

    public String getAppHdrCreDt() { return appHdrCreDt; }
    public void setAppHdrCreDt(String appHdrCreDt) { this.appHdrCreDt = appHdrCreDt; }

    public String getMsgId() { return msgId; }
    public void setMsgId(String msgId) { this.msgId = msgId; }

    public String getGrpHdrCreDtTm() { return grpHdrCreDtTm; }
    public void setGrpHdrCreDtTm(String grpHdrCreDtTm) { this.grpHdrCreDtTm = grpHdrCreDtTm; }

    public boolean isBtchBookg() { return btchBookg; }
    public void setBtchBookg(boolean btchBookg) { this.btchBookg = btchBookg; }

    public String getNbOfTxs() { return nbOfTxs; }
    public void setNbOfTxs(String nbOfTxs) { this.nbOfTxs = nbOfTxs; }

    public String getSttlmMtd() { return sttlmMtd; }
    public void setSttlmMtd(String sttlmMtd) { this.sttlmMtd = sttlmMtd; }

    public String getClrSys() { return clrSys; }
    public void setClrSys(String clrSys) { this.clrSys = clrSys; }

    public String getInstrId() { return instrId; }
    public void setInstrId(String instrId) { this.instrId = instrId; }

    public String getEndToEndId() { return endToEndId; }
    public void setEndToEndId(String endToEndId) { this.endToEndId = endToEndId; }

    public String getTxId() { return txId; }
    public void setTxId(String txId) { this.txId = txId; }

    public String getSvcLvl() { return svcLvl; }
    public void setSvcLvl(String svcLvl) { this.svcLvl = svcLvl; }

    public String getLclInstrm() { return lclInstrm; }
    public void setLclInstrm(String lclInstrm) { this.lclInstrm = lclInstrm; }

    public String getCtgyPurp() { return ctgyPurp; }
    public void setCtgyPurp(String ctgyPurp) { this.ctgyPurp = ctgyPurp; }

    public String getClrChanl() { return clrChanl; }
    public void setClrChanl(String clrChanl) { this.clrChanl = clrChanl; }

    public String getInstrPrty() { return instrPrty; }
    public void setInstrPrty(String instrPrty) { this.instrPrty = instrPrty; }

    public BigDecimal getIntrBkSttlmAmt() { return intrBkSttlmAmt; }
    public void setIntrBkSttlmAmt(BigDecimal intrBkSttlmAmt) { this.intrBkSttlmAmt = intrBkSttlmAmt; }

    public String getIntrBkSttlmAmtCcy() { return intrBkSttlmAmtCcy; }
    public void setIntrBkSttlmAmtCcy(String intrBkSttlmAmtCcy) { this.intrBkSttlmAmtCcy = intrBkSttlmAmtCcy; }

    public String getIntrBkSttlmDt() { return intrBkSttlmDt; }
    public void setIntrBkSttlmDt(String intrBkSttlmDt) { this.intrBkSttlmDt = intrBkSttlmDt; }

    public BigDecimal getInstdAmt() { return instdAmt; }
    public void setInstdAmt(BigDecimal instdAmt) { this.instdAmt = instdAmt; }

    public String getInstdAmtCcy() { return instdAmtCcy; }
    public void setInstdAmtCcy(String instdAmtCcy) { this.instdAmtCcy = instdAmtCcy; }

    public String getChrgBr() { return chrgBr; }
    public void setChrgBr(String chrgBr) { this.chrgBr = chrgBr; }

    public String getInstgAgtMmbId() { return instgAgtMmbId; }
    public void setInstgAgtMmbId(String instgAgtMmbId) { this.instgAgtMmbId = instgAgtMmbId; }

    public String getInstgAgtBic() { return instgAgtBic; }
    public void setInstgAgtBic(String instgAgtBic) { this.instgAgtBic = instgAgtBic; }

    public String getInstdAgtMmbId() { return instdAgtMmbId; }
    public void setInstdAgtMmbId(String instdAgtMmbId) { this.instdAgtMmbId = instdAgtMmbId; }

    public String getInstdAgtBic() { return instdAgtBic; }
    public void setInstdAgtBic(String instdAgtBic) { this.instdAgtBic = instdAgtBic; }

    public String getDebtorName() { return debtorName; }
    public void setDebtorName(String debtorName) { this.debtorName = debtorName; }

    public String getDebtorMobile() { return debtorMobile; }
    public void setDebtorMobile(String debtorMobile) { this.debtorMobile = debtorMobile; }

    public String getDebtorEmail() { return debtorEmail; }
    public void setDebtorEmail(String debtorEmail) { this.debtorEmail = debtorEmail; }

    public List<String> getDebtorChannelTypes() { return debtorChannelTypes; }
    public void setDebtorChannelTypes(List<String> debtorChannelTypes) { this.debtorChannelTypes = debtorChannelTypes; }

    public String getDebtorIban() { return debtorIban; }
    public void setDebtorIban(String debtorIban) { this.debtorIban = debtorIban; }

    public String getDebtorAgentMmbId() { return debtorAgentMmbId; }
    public void setDebtorAgentMmbId(String debtorAgentMmbId) { this.debtorAgentMmbId = debtorAgentMmbId; }

    public String getDebtorAgentBic() { return debtorAgentBic; }
    public void setDebtorAgentBic(String debtorAgentBic) { this.debtorAgentBic = debtorAgentBic; }

    public String getCreditorAgentMmbId() { return creditorAgentMmbId; }
    public void setCreditorAgentMmbId(String creditorAgentMmbId) { this.creditorAgentMmbId = creditorAgentMmbId; }

    public String getCreditorAgentBic() { return creditorAgentBic; }
    public void setCreditorAgentBic(String creditorAgentBic) { this.creditorAgentBic = creditorAgentBic; }

    public String getCreditorName() { return creditorName; }
    public void setCreditorName(String creditorName) { this.creditorName = creditorName; }

    public String getCreditorSubDept() { return creditorSubDept; }
    public void setCreditorSubDept(String creditorSubDept) { this.creditorSubDept = creditorSubDept; }

    public String getCreditorTownName() { return creditorTownName; }
    public void setCreditorTownName(String creditorTownName) { this.creditorTownName = creditorTownName; }

    public String getCreditorMcc() { return creditorMcc; }
    public void setCreditorMcc(String creditorMcc) { this.creditorMcc = creditorMcc; }

    public String getCreditorCountryOfRes() { return creditorCountryOfRes; }
    public void setCreditorCountryOfRes(String creditorCountryOfRes) { this.creditorCountryOfRes = creditorCountryOfRes; }

    public String getCreditorContactName() { return creditorContactName; }
    public void setCreditorContactName(String creditorContactName) { this.creditorContactName = creditorContactName; }

    public String getCreditorMobile() { return creditorMobile; }
    public void setCreditorMobile(String creditorMobile) { this.creditorMobile = creditorMobile; }

    public String getCreditorDepartment() { return creditorDepartment; }
    public void setCreditorDepartment(String creditorDepartment) { this.creditorDepartment = creditorDepartment; }

    public String getCreditorIban() { return creditorIban; }
    public void setCreditorIban(String creditorIban) { this.creditorIban = creditorIban; }

    public String getInstrInf() { return instrInf; }
    public void setInstrInf(String instrInf) { this.instrInf = instrInf; }

    public String getPurp() { return purp; }
    public void setPurp(String purp) { this.purp = purp; }

    public String getRegulatoryReportingCode() { return regulatoryReportingCode; }
    public void setRegulatoryReportingCode(String regulatoryReportingCode) { this.regulatoryReportingCode = regulatoryReportingCode; }

    public String getRegulatoryReportingInfo() { return regulatoryReportingInfo; }
    public void setRegulatoryReportingInfo(String regulatoryReportingInfo) { this.regulatoryReportingInfo = regulatoryReportingInfo; }

    public String getReferredDocType() { return referredDocType; }
    public void setReferredDocType(String referredDocType) { this.referredDocType = referredDocType; }

    public String getReferredDocRelatedDate() { return referredDocRelatedDate; }
    public void setReferredDocRelatedDate(String referredDocRelatedDate) { this.referredDocRelatedDate = referredDocRelatedDate; }

    public BigDecimal getDuePayableAmt() { return duePayableAmt; }
    public void setDuePayableAmt(BigDecimal duePayableAmt) { this.duePayableAmt = duePayableAmt; }

    public String getDuePayableAmtCcy() { return duePayableAmtCcy; }
    public void setDuePayableAmtCcy(String duePayableAmtCcy) { this.duePayableAmtCcy = duePayableAmtCcy; }

    public String getUstrd() { return ustrd; }
    public void setUstrd(String ustrd) { this.ustrd = ustrd; }
}

