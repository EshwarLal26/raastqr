package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Document {

    @XmlElement(name = "FIToFIPmtRtr")
    private FIToFIPmtRtr fiToFiPmtRtr;

    public FIToFIPmtRtr getFiToFiPmtRtr() {
        return fiToFiPmtRtr;
    }

    public void setFiToFiPmtRtr(FIToFIPmtRtr fiToFiPmtRtr) {
        this.fiToFiPmtRtr = fiToFiPmtRtr;
    }
}