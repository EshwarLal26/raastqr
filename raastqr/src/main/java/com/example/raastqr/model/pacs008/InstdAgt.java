package com.example.raastqr.model.pacs008;

import com.example.raastqr.model.pacs008.FinInstnId;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InstdAgt {
    @XmlElement(name = "FinInstnId")
    private FinInstnId finInstnId;

    public void setFinInstnId(FinInstnId finInstnId) {
        this.finInstnId = finInstnId;
    }
}