package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PmtTpInf {

    @XmlElement(name = "InstrPrty")
    private String instrPrty;

    @XmlElement(name = "ClrChanl")
    private String clrChanl;

    @XmlElement(name = "SvcLvl")
    private SvcLvl svcLvl;

    @XmlElement(name = "LclInstrm")
    private LclInstrm lclInstrm;

    @XmlElement(name = "CtgyPurp")
    private CtgyPurp ctgyPurp;

    public void setInstrPrty(String instrPrty) { this.instrPrty = instrPrty; }
    public void setClrChanl(String clrChanl) { this.clrChanl = clrChanl; }
    public void setSvcLvl(SvcLvl svcLvl) { this.svcLvl = svcLvl; }
    public void setLclInstrm(LclInstrm lclInstrm) { this.lclInstrm = lclInstrm; }
    public void setCtgyPurp(CtgyPurp ctgyPurp) { this.ctgyPurp = ctgyPurp; }
}
