package com.example.server.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentTypeInformation {

    @XmlElement(name = "ClrChanl")
    private String clrChanl;

    @XmlElement(name = "SvcLvl")
    private ProprietaryValue svcLvl;

    @XmlElement(name = "LclInstrm")
    private ProprietaryValue lclInstrm;

    @XmlElement(name = "CtgyPurp")
    private ProprietaryValue ctgyPurp;

    public String getClrChanl() {
        return clrChanl;
    }

    public void setClrChanl(String clrChanl) {
        this.clrChanl = clrChanl;
    }

    public ProprietaryValue getSvcLvl() {
        return svcLvl;
    }

    public void setSvcLvl(ProprietaryValue svcLvl) {
        this.svcLvl = svcLvl;
    }

    public ProprietaryValue getLclInstrm() {
        return lclInstrm;
    }

    public void setLclInstrm(ProprietaryValue lclInstrm) {
        this.lclInstrm = lclInstrm;
    }

    public ProprietaryValue getCtgyPurp() {
        return ctgyPurp;
    }

    public void setCtgyPurp(ProprietaryValue ctgyPurp) {
        this.ctgyPurp = ctgyPurp;
    }
}