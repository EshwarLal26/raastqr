package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RgltryRptg {

    @XmlElement(name = "Dtls")
    private RgltryRptgDtls dtls;

    public void setDtls(RgltryRptgDtls dtls) {
        this.dtls = dtls;
    }
}
