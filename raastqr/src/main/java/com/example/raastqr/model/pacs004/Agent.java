package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Agent {

    @XmlElement(name = "FinInstnId")
    private FinInstnId finInstnId;

    public FinInstnId getFinInstnId() {
        return finInstnId;
    }

    public void setFinInstnId(FinInstnId finInstnId) {
        this.finInstnId = finInstnId;
    }
}