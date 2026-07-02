package com.example.raastqr.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DataPDU", namespace = "urn:cma:stp:xsd:stp.1.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataPDU {

    @XmlElement(name = "Body", namespace = "urn:cma:stp:xsd:stp.1.0")
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}