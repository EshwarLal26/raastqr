package com.example.raastqr.model.pacs008;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Amount {

    @XmlValue
    private BigDecimal value;

    @XmlAttribute(name = "Ccy")
    private String ccy;

    public void setValue(BigDecimal value) { this.value = value; }
    public void setCcy(String ccy) { this.ccy = ccy; }
}