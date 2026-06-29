package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Body {

    @XmlElement(name = "AppHdr")
    private AppHdr appHdr;

    @XmlElement(name = "Document")
    private Document document;

    public void setAppHdr(AppHdr appHdr) {
        this.appHdr = appHdr;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}