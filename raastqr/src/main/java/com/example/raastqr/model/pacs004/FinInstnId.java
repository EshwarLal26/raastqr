package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.*;

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