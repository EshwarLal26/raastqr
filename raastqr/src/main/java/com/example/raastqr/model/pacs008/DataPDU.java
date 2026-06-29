package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DataPDU")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataPDU {

    @XmlElement(name = "Body")
    private Body body;

    public void setBody(Body body) {
        this.body = body;
    }
}