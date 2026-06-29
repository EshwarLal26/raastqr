package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PmtId {
    @XmlElement(name = "InstrId")
    private String instrId;

    @XmlElement(name = "EndToEndId")
    private String endToEndId;

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public void setInstrId(String instrId) {
        this.instrId = instrId;
    }

    @XmlElement(name = "TxId")
    private String txId;
}