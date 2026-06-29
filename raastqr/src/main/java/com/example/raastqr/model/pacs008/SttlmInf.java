package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlElement;

public class SttlmInf {
    @XmlElement(name = "SttlmMtd")
    private String sttlmMtd;
    
    public void setSttlmMtd(String sttlmMtd) {
    this.sttlmMtd = sttlmMtd;
}
}