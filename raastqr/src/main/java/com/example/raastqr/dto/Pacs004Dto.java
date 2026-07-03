package com.example.raastqr.dto;

import java.math.BigDecimal;

public class Pacs004Dto {

    // AppHdr
    private String fromMemberId;
    private String toMemberId;
    private String bizMsgIdr;
    private String msgDefIdr;
    private String bizSvc;
    private String appHdrCreDt;

    // GrpHdr
    private String grpHdrMsgId;
    private String grpHdrCreDtTm;
    private String nbOfTxs;
    private String sttlmMtd;

    // TxInf
    private String rtrId;

    // Original message references
    private String orgnlMsgId;
    private String orgnlMsgNmId;
    private String orgnlInstrId;
    private String orgnlEndToEndId;
    private String orgnlTxId;

    // Amounts
    private BigDecimal orgnlIntrBkSttlmAmt;
    private String orgnlIntrBkSttlmAmtCcy;
    private BigDecimal rtrdIntrBkSttlmAmt;
    private String rtrdIntrBkSttlmAmtCcy;
    private String intrBkSttlmDt;
    private String chrgBr;

    // Return reason
    private String returnReasonOriginatorName;
    private String returnReasonCode;
    private String returnAdditionalInfo;

    // Agents
    private String instgAgtMmbId;
    private String instdAgtMmbId;
    private String dbtrAgtMmbId;
    private String cdtrAgtMmbId;

    // Parties / accounts
    private String dbtrName;
    private String dbtrIban;
    private String cdtrName;
    private String cdtrIban;

    public String getFromMemberId() {
        return fromMemberId;
    }

    public void setFromMemberId(String fromMemberId) {
        this.fromMemberId = fromMemberId;
    }

    public String getToMemberId() {
        return toMemberId;
    }

    public void setToMemberId(String toMemberId) {
        this.toMemberId = toMemberId;
    }

    public String getBizMsgIdr() {
        return bizMsgIdr;
    }

    public void setBizMsgIdr(String bizMsgIdr) {
        this.bizMsgIdr = bizMsgIdr;
    }

    public String getMsgDefIdr() {
        return msgDefIdr;
    }

    public void setMsgDefIdr(String msgDefIdr) {
        this.msgDefIdr = msgDefIdr;
    }

    public String getBizSvc() {
        return bizSvc;
    }

    public void setBizSvc(String bizSvc) {
        this.bizSvc = bizSvc;
    }

    public String getAppHdrCreDt() {
        return appHdrCreDt;
    }

    public void setAppHdrCreDt(String appHdrCreDt) {
        this.appHdrCreDt = appHdrCreDt;
    }

    public String getGrpHdrMsgId() {
        return grpHdrMsgId;
    }

    public void setGrpHdrMsgId(String grpHdrMsgId) {
        this.grpHdrMsgId = grpHdrMsgId;
    }

    public String getGrpHdrCreDtTm() {
        return grpHdrCreDtTm;
    }

    public void setGrpHdrCreDtTm(String grpHdrCreDtTm) {
        this.grpHdrCreDtTm = grpHdrCreDtTm;
    }

    public String getNbOfTxs() {
        return nbOfTxs;
    }

    public void setNbOfTxs(String nbOfTxs) {
        this.nbOfTxs = nbOfTxs;
    }

    public String getSttlmMtd() {
        return sttlmMtd;
    }

    public void setSttlmMtd(String sttlmMtd) {
        this.sttlmMtd = sttlmMtd;
    }

    public String getRtrId() {
        return rtrId;
    }

    public void setRtrId(String rtrId) {
        this.rtrId = rtrId;
    }

    public String getOrgnlMsgId() {
        return orgnlMsgId;
    }

    public void setOrgnlMsgId(String orgnlMsgId) {
        this.orgnlMsgId = orgnlMsgId;
    }

    public String getOrgnlMsgNmId() {
        return orgnlMsgNmId;
    }

    public void setOrgnlMsgNmId(String orgnlMsgNmId) {
        this.orgnlMsgNmId = orgnlMsgNmId;
    }

    public String getOrgnlInstrId() {
        return orgnlInstrId;
    }

    public void setOrgnlInstrId(String orgnlInstrId) {
        this.orgnlInstrId = orgnlInstrId;
    }

    public String getOrgnlEndToEndId() {
        return orgnlEndToEndId;
    }

    public void setOrgnlEndToEndId(String orgnlEndToEndId) {
        this.orgnlEndToEndId = orgnlEndToEndId;
    }

    public String getOrgnlTxId() {
        return orgnlTxId;
    }

    public void setOrgnlTxId(String orgnlTxId) {
        this.orgnlTxId = orgnlTxId;
    }

    public BigDecimal getOrgnlIntrBkSttlmAmt() {
        return orgnlIntrBkSttlmAmt;
    }

    public void setOrgnlIntrBkSttlmAmt(BigDecimal orgnlIntrBkSttlmAmt) {
        this.orgnlIntrBkSttlmAmt = orgnlIntrBkSttlmAmt;
    }

    public String getOrgnlIntrBkSttlmAmtCcy() {
        return orgnlIntrBkSttlmAmtCcy;
    }

    public void setOrgnlIntrBkSttlmAmtCcy(String orgnlIntrBkSttlmAmtCcy) {
        this.orgnlIntrBkSttlmAmtCcy = orgnlIntrBkSttlmAmtCcy;
    }

    public BigDecimal getRtrdIntrBkSttlmAmt() {
        return rtrdIntrBkSttlmAmt;
    }

    public void setRtrdIntrBkSttlmAmt(BigDecimal rtrdIntrBkSttlmAmt) {
        this.rtrdIntrBkSttlmAmt = rtrdIntrBkSttlmAmt;
    }

    public String getRtrdIntrBkSttlmAmtCcy() {
        return rtrdIntrBkSttlmAmtCcy;
    }

    public void setRtrdIntrBkSttlmAmtCcy(String rtrdIntrBkSttlmAmtCcy) {
        this.rtrdIntrBkSttlmAmtCcy = rtrdIntrBkSttlmAmtCcy;
    }

    public String getIntrBkSttlmDt() {
        return intrBkSttlmDt;
    }

    public void setIntrBkSttlmDt(String intrBkSttlmDt) {
        this.intrBkSttlmDt = intrBkSttlmDt;
    }

    public String getChrgBr() {
        return chrgBr;
    }

    public void setChrgBr(String chrgBr) {
        this.chrgBr = chrgBr;
    }

    public String getReturnReasonOriginatorName() {
        return returnReasonOriginatorName;
    }

    public void setReturnReasonOriginatorName(String returnReasonOriginatorName) {
        this.returnReasonOriginatorName = returnReasonOriginatorName;
    }

    public String getReturnReasonCode() {
        return returnReasonCode;
    }

    public void setReturnReasonCode(String returnReasonCode) {
        this.returnReasonCode = returnReasonCode;
    }

    public String getReturnAdditionalInfo() {
        return returnAdditionalInfo;
    }

    public void setReturnAdditionalInfo(String returnAdditionalInfo) {
        this.returnAdditionalInfo = returnAdditionalInfo;
    }

    public String getInstgAgtMmbId() {
        return instgAgtMmbId;
    }

    public void setInstgAgtMmbId(String instgAgtMmbId) {
        this.instgAgtMmbId = instgAgtMmbId;
    }

    public String getInstdAgtMmbId() {
        return instdAgtMmbId;
    }

    public void setInstdAgtMmbId(String instdAgtMmbId) {
        this.instdAgtMmbId = instdAgtMmbId;
    }

    public String getDbtrAgtMmbId() {
        return dbtrAgtMmbId;
    }

    public void setDbtrAgtMmbId(String dbtrAgtMmbId) {
        this.dbtrAgtMmbId = dbtrAgtMmbId;
    }

    public String getCdtrAgtMmbId() {
        return cdtrAgtMmbId;
    }

    public void setCdtrAgtMmbId(String cdtrAgtMmbId) {
        this.cdtrAgtMmbId = cdtrAgtMmbId;
    }

    public String getDbtrName() {
        return dbtrName;
    }

    public void setDbtrName(String dbtrName) {
        this.dbtrName = dbtrName;
    }

    public String getDbtrIban() {
        return dbtrIban;
    }

    public void setDbtrIban(String dbtrIban) {
        this.dbtrIban = dbtrIban;
    }

    public String getCdtrName() {
        return cdtrName;
    }

    public void setCdtrName(String cdtrName) {
        this.cdtrName = cdtrName;
    }

    public String getCdtrIban() {
        return cdtrIban;
    }

    public void setCdtrIban(String cdtrIban) {
        this.cdtrIban = cdtrIban;
    }
}