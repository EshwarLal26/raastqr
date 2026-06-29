package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CdtTrfTxInf {
    @XmlElement(name = "PmtId")
    private PmtId pmtId;

    @XmlElement(name = "IntrBkSttlmAmt")
    private IntrBkSttlmAmt intrBkSttlmAmt;

    public void setDbtr(Dbtr dbtr) {
        this.dbtr = dbtr;
    }

    public void setPmtId(PmtId pmtId) {
        this.pmtId = pmtId;
    }

    public void setIntrBkSttlmAmt(IntrBkSttlmAmt intrBkSttlmAmt) {
        this.intrBkSttlmAmt = intrBkSttlmAmt;
    }

    public void setDbtrAgt(DbtrAgt dbtrAgt) {
        this.dbtrAgt = dbtrAgt;
    }

    public void setCdtrAcct(CdtrAcct cdtrAcct) {
        this.cdtrAcct = cdtrAcct;
    }

    public void setRmtInf(RmtInf rmtInf) {
        this.rmtInf = rmtInf;
    }

    public void setChrgBr(String chrgBr) {
        this.chrgBr = chrgBr;
    }

    public void setDbtrAcct(DbtrAcct dbtrAcct) {
        this.dbtrAcct = dbtrAcct;
    }

    @XmlElement(name = "Dbtr")
    private Dbtr dbtr;

    @XmlElement(name = "DbtrAcct")
    private DbtrAcct dbtrAcct;

    @XmlElement(name = "DbtrAgt")
    private DbtrAgt dbtrAgt;

    @XmlElement(name = "CdtrAcct")
    private CdtrAcct cdtrAcct;

    @XmlElement(name = "RmtInf")
    private RmtInf rmtInf;

    @XmlElement(name = "ChrgBr")
    private String chrgBr;
}