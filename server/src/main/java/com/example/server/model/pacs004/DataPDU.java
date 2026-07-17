package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DataPDU", namespace = "urn:cma:stp:xsd:stp.1.0")
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