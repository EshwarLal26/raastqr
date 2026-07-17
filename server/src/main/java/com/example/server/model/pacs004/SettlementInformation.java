package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SettlementInformation {

    @XmlElement(name = "SttlmMtd")
    private String sttlmMtd;

    public String getSttlmMtd() {
        return sttlmMtd;
    }

    public void setSttlmMtd(String sttlmMtd) {
        this.sttlmMtd = sttlmMtd;
    }
}