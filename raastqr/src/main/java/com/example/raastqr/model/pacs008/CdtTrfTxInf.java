package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CdtTrfTxInf {
    @XmlElement(name = "PmtId")
    private PmtId pmtId;

    @XmlElement(name = "PmtTpInf")
    private PmtTpInf pmtTpInf;

    @XmlElement(name = "IntrBkSttlmAmt")
    private IntrBkSttlmAmt intrBkSttlmAmt;

    @XmlElement(name = "IntrBkSttlmDt")
    private String intrBkSttlmDt;

    @XmlElement(name = "InstdAmt")
    private Amount instdAmt;

    @XmlElement(name = "ChrgBr")
    private String chrgBr;

    @XmlElement(name = "InstgAgt")
    private InstgAgt instgAgt;

    @XmlElement(name = "InstdAgt")
    private InstdAgt instdAgt;

    @XmlElement(name = "Dbtr")
    private Dbtr dbtr;

    @XmlElement(name = "DbtrAcct")
    private DbtrAcct dbtrAcct;

    @XmlElement(name = "DbtrAgt")
    private DbtrAgt dbtrAgt;

    @XmlElement(name = "CdtrAgt")
    private CdtrAgt cdtrAgt;

    @XmlElement(name = "Cdtr")
    private Cdtr cdtr;

    @XmlElement(name = "CdtrAcct")
    private CdtrAcct cdtrAcct;

    @XmlElement(name = "InstrForNxtAgt")
    private InstrForNxtAgt instrForNxtAgt;

    @XmlElement(name = "Purp")
    private Purp purp;

    @XmlElement(name = "RgltryRptg")
    private RgltryRptg rgltryRptg;

    @XmlElement(name = "RmtInf")
    private RmtInf rmtInf;

    public void setPmtId(PmtId pmtId) { this.pmtId = pmtId; }
    public void setPmtTpInf(PmtTpInf pmtTpInf) { this.pmtTpInf = pmtTpInf; }
    public void setIntrBkSttlmAmt(IntrBkSttlmAmt intrBkSttlmAmt) { this.intrBkSttlmAmt = intrBkSttlmAmt; }
    public void setIntrBkSttlmDt(String intrBkSttlmDt) { this.intrBkSttlmDt = intrBkSttlmDt; }
    public void setInstdAmt(Amount instdAmt) { this.instdAmt = instdAmt; }
    public void setChrgBr(String chrgBr) { this.chrgBr = chrgBr; }
    public void setInstgAgt(InstgAgt instgAgt) { this.instgAgt = instgAgt; }
    public void setInstdAgt(InstdAgt instdAgt) { this.instdAgt = instdAgt; }
    public void setDbtr(Dbtr dbtr) { this.dbtr = dbtr; }
    public void setDbtrAcct(DbtrAcct dbtrAcct) { this.dbtrAcct = dbtrAcct; }
    public void setDbtrAgt(DbtrAgt dbtrAgt) { this.dbtrAgt = dbtrAgt; }
    public void setCdtrAgt(CdtrAgt cdtrAgt) { this.cdtrAgt = cdtrAgt; }
    public void setCdtr(Cdtr cdtr) { this.cdtr = cdtr; }
    public void setCdtrAcct(CdtrAcct cdtrAcct) { this.cdtrAcct = cdtrAcct; }
    public void setInstrForNxtAgt(InstrForNxtAgt instrForNxtAgt) { this.instrForNxtAgt = instrForNxtAgt; }
    public void setPurp(Purp purp) { this.purp = purp; }
    public void setRgltryRptg(RgltryRptg rgltryRptg) { this.rgltryRptg = rgltryRptg; }
    public void setRmtInf(RmtInf rmtInf) { this.rmtInf = rmtInf; }
}
