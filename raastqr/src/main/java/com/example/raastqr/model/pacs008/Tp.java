package com.example.raastqr.model.pacs008;

import com.example.raastqr.model.pacs008.CdOrPrtry;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tp {
    @XmlElement(name = "CdOrPrtry")
    private CdOrPrtry cdOrPrtry;

    public void setCdOrPrtry(CdOrPrtry cdOrPrtry) {
        this.cdOrPrtry = cdOrPrtry;
    }
}