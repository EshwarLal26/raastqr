package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlElement;

public class Document {
    @XmlElement(name = "FIToFICstmrCdtTrf")
    private FIToFICstmrCdtTrf fiToFiCstmrCdtTrf;

    
    public void setFiToFiCstmrCdtTrf(FIToFICstmrCdtTrf fiToFiCstmrCdtTrf) {
    this.fiToFiCstmrCdtTrf = fiToFiCstmrCdtTrf;
}
}