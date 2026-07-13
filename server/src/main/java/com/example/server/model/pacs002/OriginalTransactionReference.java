package com.example.server.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OriginalTransactionReference {

    @XmlElement(name = "IntrBkSttlmAmt")
    private IntrBkSttlmAmt intrBkSttlmAmt;

    @XmlElement(name = "IntrBkSttlmDt")
    private String intrBkSttlmDt;

    @XmlElement(name = "PmtTpInf")
    private PaymentTypeInformation pmtTpInf;

    @XmlElement(name = "Purp")
    private ProprietaryValue purp;

    public IntrBkSttlmAmt getIntrBkSttlmAmt() {
        return intrBkSttlmAmt;
    }

    public void setIntrBkSttlmAmt(IntrBkSttlmAmt intrBkSttlmAmt) {
        this.intrBkSttlmAmt = intrBkSttlmAmt;
    }

    public String getIntrBkSttlmDt() {
        return intrBkSttlmDt;
    }

    public void setIntrBkSttlmDt(String intrBkSttlmDt) {
        this.intrBkSttlmDt = intrBkSttlmDt;
    }

    public PaymentTypeInformation getPmtTpInf() {
        return pmtTpInf;
    }

    public void setPmtTpInf(PaymentTypeInformation pmtTpInf) {
        this.pmtTpInf = pmtTpInf;
    }

    public ProprietaryValue getPurp() {
        return purp;
    }

    public void setPurp(ProprietaryValue purp) {
        this.purp = purp;
    }
}