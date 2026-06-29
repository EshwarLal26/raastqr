package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class LclInstrm {
    @XmlElement(name = "Prtry")
    private String prtry;

    public void setPrtry(String prtry) {
        this.prtry = prtry;
    }
}