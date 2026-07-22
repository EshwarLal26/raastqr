package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RgltryRptgDtls {

    @XmlElement(name = "Cd")
    private String cd;

    @XmlElement(name = "Inf")
    private String inf;

    public void setCd(String cd) {
        this.cd = cd;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }
}
