package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class IntrBkSttlmAmt {
    @XmlValue
    private BigDecimal value;

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    @XmlAttribute(name = "Ccy")
    private String ccy;
}