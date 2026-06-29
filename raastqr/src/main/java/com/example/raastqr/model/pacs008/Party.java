package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Party {

    @XmlElement(name = "FIId")
    private FIId fiId;

    public void setFIId(FIId fiId) {
        this.fiId = fiId;
    }
}