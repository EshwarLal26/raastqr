package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FinInstnId {
    @XmlElement(name = "BICFI")
    private String bicFi;

    @XmlElement(name = "ClrSysMmbId")
    private ClrSysMmbId clrSysMmbId;

    public void setBicFi(String bicFi) {
        this.bicFi = bicFi;
    }

    public void setClrSysMmbId(ClrSysMmbId clrSysMmbId) {
        this.clrSysMmbId = clrSysMmbId;
    }
}
