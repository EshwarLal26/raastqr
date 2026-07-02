package com.example.raastqr.model.pacs002;

import org.w3c.dom.Element;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SignatureWrapper {

    @XmlAnyElement(lax = false)
    private Element signature;

    public Element getSignature() {
        return signature;
    }

    public void setSignature(Element signature) {
        this.signature = signature;
    }
}