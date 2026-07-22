package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ClrSys {

    @XmlElement(name = "Cd")
    private String cd;

    public void setCd(String cd) {
        this.cd = cd;
    }
}
