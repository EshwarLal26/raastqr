package com.example.raastqr.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FinInstnId {

    @XmlElement(name = "ClrSysMmbId")
    private ClrSysMmbId clrSysMmbId;

    public ClrSysMmbId getClrSysMmbId() {
        return clrSysMmbId;
    }

    public void setClrSysMmbId(ClrSysMmbId clrSysMmbId) {
        this.clrSysMmbId = clrSysMmbId;
    }
}