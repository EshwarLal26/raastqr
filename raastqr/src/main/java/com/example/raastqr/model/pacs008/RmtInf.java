package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RmtInf {

    @XmlElement(name = "Ustrd")
    private String ustrd;

     @XmlElement(name = "Strd")
    private Strd strd;

    public void setUstrd(String ustrd) {
        this.ustrd = ustrd;
    }

   

   public void setStrd(Strd strd) {
    this.strd = strd;
}
}