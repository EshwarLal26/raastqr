package com.example.raastqr.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Party {

    @XmlElement(name = "FIId")
    private FIId fiId;

    public FIId getFiId() {
        return fiId;
    }

    public void setFiId(FIId fiId) {
        this.fiId = fiId;
    }
}