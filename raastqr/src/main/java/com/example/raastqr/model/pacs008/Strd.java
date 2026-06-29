package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Strd {

    @XmlElement(name = "RfrdDocInf")
    private RfrdDocInf rfrdDocInf;

    @XmlElement(name = "RfrdDocAmt")
    private RfrdDocAmt rfrdDocAmt;

    public void setRfrdDocInf(RfrdDocInf rfrdDocInf) {
        this.rfrdDocInf = rfrdDocInf;
    }

    public void setRfrdDocAmt(RfrdDocAmt rfrdDocAmt) {
        this.rfrdDocAmt = rfrdDocAmt;
    }
}