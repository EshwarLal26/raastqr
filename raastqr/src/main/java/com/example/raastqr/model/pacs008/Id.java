package com.example.raastqr.model.pacs008;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)

public class Id {
    public void setIban(String iban) {
        this.iban = iban;
    }

    @XmlElement(name = "IBAN")
    private String iban;
}
