package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FinInstnId {
    @XmlElement(name = "BICFI")
    private String bicfi;


    public void setBicfi(String bicfi) {
    this.bicfi = bicfi;
}
}