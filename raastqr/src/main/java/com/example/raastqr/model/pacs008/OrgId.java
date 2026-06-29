package com.example.raastqr.model.pacs008;

import com.example.raastqr.model.pacs008.OthrId;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrgId {
    @XmlElement(name = "Othr")
    private OthrId othr;

    public void setOthr(OthrId othr) {
        this.othr = othr;
    }
}