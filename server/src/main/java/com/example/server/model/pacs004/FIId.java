package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class FIId {

    @XmlElement(name = "FinInstnId")
    private FinInstnId finInstnId;

    public FinInstnId getFinInstnId() {
        return finInstnId;
    }

    public void setFinInstnId(FinInstnId finInstnId) {
        this.finInstnId = finInstnId;
    }
}