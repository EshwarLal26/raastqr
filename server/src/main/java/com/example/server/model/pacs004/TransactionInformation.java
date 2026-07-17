package com.example.server.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionInformation {

    @XmlElement(name = "RtrId")
    private String rtrId;

    @XmlElement(name = "OrgnlGrpInf")
    private OriginalGroupInformation orgnlGrpInf;

    @XmlElement(name = "OrgnlInstrId")
    private String orgnlInstrId;

    @XmlElement(name = "OrgnlEndToEndId")
    private String orgnlEndToEndId;

    @XmlElement(name = "OrgnlTxId")
    private String orgnlTxId;

    @XmlElement(name = "OrgnlIntrBkSttlmAmt")
    private CurrencyAmount orgnlIntrBkSttlmAmt;

    @XmlElement(name = "RtrdIntrBkSttlmAmt")
    private CurrencyAmount rtrdIntrBkSttlmAmt;

    @XmlElement(name = "IntrBkSttlmDt")
    private String intrBkSttlmDt;

    @XmlElement(name = "ChrgBr")
    private String chrgBr;

    @XmlElement(name = "RtrRsnInf")
    private ReturnReasonInformation rtrRsnInf;

    @XmlElement(name = "InstgAgt")
    private Agent instgAgt;

    @XmlElement(name = "InstdAgt")
    private Agent instdAgt;

    @XmlElement(name = "Dbtr")
    private NameOnlyParty dbtr;

    @XmlElement(name = "DbtrAcct")
    private CashAccount dbtrAcct;

    @XmlElement(name = "DbtrAgt")
    private Agent dbtrAgt;

    @XmlElement(name = "CdtrAgt")
    private Agent cdtrAgt;

    @XmlElement(name = "Cdtr")
    private NameOnlyParty cdtr;

    @XmlElement(name = "CdtrAcct")
    private CashAccount cdtrAcct;

    public String getRtrId() { return rtrId; }
    public void setRtrId(String rtrId) { this.rtrId = rtrId; }

    public OriginalGroupInformation getOrgnlGrpInf() { return orgnlGrpInf; }
    public void setOrgnlGrpInf(OriginalGroupInformation orgnlGrpInf) { this.orgnlGrpInf = orgnlGrpInf; }

    public String getOrgnlInstrId() { return orgnlInstrId; }
    public void setOrgnlInstrId(String orgnlInstrId) { this.orgnlInstrId = orgnlInstrId; }

    public String getOrgnlEndToEndId() { return orgnlEndToEndId; }
    public void setOrgnlEndToEndId(String orgnlEndToEndId) { this.orgnlEndToEndId = orgnlEndToEndId; }

    public String getOrgnlTxId() { return orgnlTxId; }
    public void setOrgnlTxId(String orgnlTxId) { this.orgnlTxId = orgnlTxId; }

    public CurrencyAmount getOrgnlIntrBkSttlmAmt() { return orgnlIntrBkSttlmAmt; }
    public void setOrgnlIntrBkSttlmAmt(CurrencyAmount orgnlIntrBkSttlmAmt) { this.orgnlIntrBkSttlmAmt = orgnlIntrBkSttlmAmt; }

    public CurrencyAmount getRtrdIntrBkSttlmAmt() { return rtrdIntrBkSttlmAmt; }
    public void setRtrdIntrBkSttlmAmt(CurrencyAmount rtrdIntrBkSttlmAmt) { this.rtrdIntrBkSttlmAmt = rtrdIntrBkSttlmAmt; }

    public String getIntrBkSttlmDt() { return intrBkSttlmDt; }
    public void setIntrBkSttlmDt(String intrBkSttlmDt) { this.intrBkSttlmDt = intrBkSttlmDt; }

    public String getChrgBr() { return chrgBr; }
    public void setChrgBr(String chrgBr) { this.chrgBr = chrgBr; }

    public ReturnReasonInformation getRtrRsnInf() { return rtrRsnInf; }
    public void setRtrRsnInf(ReturnReasonInformation rtrRsnInf) { this.rtrRsnInf = rtrRsnInf; }

    public Agent getInstgAgt() { return instgAgt; }
    public void setInstgAgt(Agent instgAgt) { this.instgAgt = instgAgt; }

    public Agent getInstdAgt() { return instdAgt; }
    public void setInstdAgt(Agent instdAgt) { this.instdAgt = instdAgt; }

    public NameOnlyParty getDbtr() { return dbtr; }
    public void setDbtr(NameOnlyParty dbtr) { this.dbtr = dbtr; }

    public CashAccount getDbtrAcct() { return dbtrAcct; }
    public void setDbtrAcct(CashAccount dbtrAcct) { this.dbtrAcct = dbtrAcct; }

    public Agent getDbtrAgt() { return dbtrAgt; }
    public void setDbtrAgt(Agent dbtrAgt) { this.dbtrAgt = dbtrAgt; }

    public Agent getCdtrAgt() { return cdtrAgt; }
    public void setCdtrAgt(Agent cdtrAgt) { this.cdtrAgt = cdtrAgt; }

    public NameOnlyParty getCdtr() { return cdtr; }
    public void setCdtr(NameOnlyParty cdtr) { this.cdtr = cdtr; }

    public CashAccount getCdtrAcct() { return cdtrAcct; }
    public void setCdtrAcct(CashAccount cdtrAcct) { this.cdtrAcct = cdtrAcct; }
}
