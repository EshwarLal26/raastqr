package com.example.raastqr.model.pacs008;

import com.example.raastqr.model.pacs008.SchemeNm;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OthrId {

    @XmlElement(name = "Id")
    private String id;

    @XmlElement(name = "SchmeNm")
    private SchemeNm schmeNm;

    public void setId(String id) { this.id = id; }
    public void setSchmeNm(SchemeNm schmeNm) { this.schmeNm = schmeNm; }
}