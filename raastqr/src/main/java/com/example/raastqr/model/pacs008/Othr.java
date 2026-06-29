package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Othr {
    @XmlElement(name = "ChanlTp")
    private String chanlTp;

    public void setChanlTp(String chanlTp) {
        this.chanlTp = chanlTp;
    }
}