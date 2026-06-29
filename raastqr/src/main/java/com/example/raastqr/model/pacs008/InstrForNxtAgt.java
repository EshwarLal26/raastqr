package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InstrForNxtAgt {
    @XmlElement(name = "InstrInf")
    private String instrInf;

    public void setInstrInf(String instrInf) {
        this.instrInf = instrInf;
    }
}