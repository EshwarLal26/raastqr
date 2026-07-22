package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SttlmInf {
    @XmlElement(name = "SttlmMtd")
    private String sttlmMtd;

    @XmlElement(name = "ClrSys")
    private ClrSys clrSys;
    
    public void setSttlmMtd(String sttlmMtd) {
        this.sttlmMtd = sttlmMtd;
    }

    public void setClrSys(ClrSys clrSys) {
        this.clrSys = clrSys;
    }
}
