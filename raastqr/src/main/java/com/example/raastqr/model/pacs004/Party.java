package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Party {

    @XmlElement(name = "FIId")
    private FIId fiId;

    public FIId getFIId() {
        return fiId;
    }

    public void setFIId(FIId fiId) {
        this.fiId = fiId;
    }
}