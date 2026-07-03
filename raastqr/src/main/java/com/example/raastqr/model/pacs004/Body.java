package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Body {

    @XmlElement(name = "AppHdr", namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01")
    private AppHdr appHdr;

    @XmlElement(name = "Document", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.004.001.09")
    private Document document;

    public AppHdr getAppHdr() {
        return appHdr;
    }

    public void setAppHdr(AppHdr appHdr) {
        this.appHdr = appHdr;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}