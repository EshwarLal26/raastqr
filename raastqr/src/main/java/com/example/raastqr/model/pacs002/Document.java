package com.example.raastqr.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Document {

    @XmlElement(name = "FIToFIPmtStsRpt", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.002.001.10")
    private FIToFIPmtStsRpt fiToFiPmtStsRpt;

    public FIToFIPmtStsRpt getFiToFiPmtStsRpt() {
        return fiToFiPmtStsRpt;
    }

    public void setFiToFiPmtStsRpt(FIToFIPmtStsRpt fiToFiPmtStsRpt) {
        this.fiToFiPmtStsRpt = fiToFiPmtStsRpt;
    }
}