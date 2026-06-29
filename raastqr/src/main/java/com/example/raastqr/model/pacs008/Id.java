package com.example.raastqr.model.pacs008;
import jakarta.xml.bind.annotation.XmlElement;
public class Id {
    @XmlElement(name = "IBAN")
    private String iban;
    
    public void setIban(String iban) {
    this.iban = iban;
}
}
