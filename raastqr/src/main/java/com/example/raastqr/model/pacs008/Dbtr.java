package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dbtr {

    @XmlElement(name = "Nm")
    private String nm;

    @XmlElement(name = "CtctDtls")
    private CtctDtls ctctDtls;

    public void setNm(String nm) {
        this.nm = nm;
    }

    public void setCtctDtls(CtctDtls ctctDtls) {
        this.ctctDtls = ctctDtls;
    }
}