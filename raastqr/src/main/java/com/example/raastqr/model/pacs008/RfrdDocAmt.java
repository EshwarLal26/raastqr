package com.example.raastqr.model.pacs008;

import com.example.raastqr.model.pacs008.Amount;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RfrdDocAmt {
    @XmlElement(name = "DuePyblAmt")
    private Amount duePyblAmt;

    public void setDuePyblAmt(Amount duePyblAmt) {
        this.duePyblAmt = duePyblAmt;
    }
}