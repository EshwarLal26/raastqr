package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ClrSysMmbId {

    @XmlElement(name = "MmbId")
    private String mmbId;

    public void setMmbId(String mmbId) {
        this.mmbId = mmbId;
    }
}