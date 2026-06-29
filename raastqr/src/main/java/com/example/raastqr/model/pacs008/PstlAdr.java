package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PstlAdr {
    @XmlElement(name = "SubDept")
    private String subDept;

    @XmlElement(name = "TwnNm")
    private String twnNm;

    public void setSubDept(String subDept) { this.subDept = subDept; }
    public void setTwnNm(String twnNm) { this.twnNm = twnNm; }
}