package com.example.raastqr.model.pacs008;

import com.example.raastqr.model.pacs008.Tp;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RfrdDocInf {

    @XmlElement(name = "Tp")
    private Tp tp;

    @XmlElement(name = "RltdDt")
    private String rltdDt;

    public void setTp(Tp tp) { this.tp = tp; }
    public void setRltdDt(String rltdDt) { this.rltdDt = rltdDt; }
}