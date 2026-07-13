package com.example.server.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionInfoAndStatus {

    @XmlElement(name = "StsId")
    private String stsId;

    @XmlElement(name = "OrgnlInstrId")
    private String orgnlInstrId;

    @XmlElement(name = "OrgnlEndToEndId")
    private String orgnlEndToEndId;

    @XmlElement(name = "OrgnlTxId")
    private String orgnlTxId;

    @XmlElement(name = "TxSts")
    private String txSts;

    @XmlElement(name = "AcctSvcrRef")
    private String acctSvcrRef;

    @XmlElement(name = "InstgAgt")
    private Agent instgAgt;

    @XmlElement(name = "InstdAgt")
    private Agent instdAgt;

    @XmlElement(name = "OrgnlTxRef")
    private OriginalTransactionReference orgnlTxRef;

    public String getStsId() {
        return stsId;
    }

    public void setStsId(String stsId) {
        this.stsId = stsId;
    }

    public String getOrgnlInstrId() {
        return orgnlInstrId;
    }

    public void setOrgnlInstrId(String orgnlInstrId) {
        this.orgnlInstrId = orgnlInstrId;
    }

    public String getOrgnlEndToEndId() {
        return orgnlEndToEndId;
    }

    public void setOrgnlEndToEndId(String orgnlEndToEndId) {
        this.orgnlEndToEndId = orgnlEndToEndId;
    }

    public String getOrgnlTxId() {
        return orgnlTxId;
    }

    public void setOrgnlTxId(String orgnlTxId) {
        this.orgnlTxId = orgnlTxId;
    }

    public String getTxSts() {
        return txSts;
    }

    public void setTxSts(String txSts) {
        this.txSts = txSts;
    }

    public String getAcctSvcrRef() {
        return acctSvcrRef;
    }

    public void setAcctSvcrRef(String acctSvcrRef) {
        this.acctSvcrRef = acctSvcrRef;
    }

    public Agent getInstgAgt() {
        return instgAgt;
    }

    public void setInstgAgt(Agent instgAgt) {
        this.instgAgt = instgAgt;
    }

    public Agent getInstdAgt() {
        return instdAgt;
    }

    public void setInstdAgt(Agent instdAgt) {
        this.instdAgt = instdAgt;
    }

    public OriginalTransactionReference getOrgnlTxRef() {
        return orgnlTxRef;
    }

    public void setOrgnlTxRef(OriginalTransactionReference orgnlTxRef) {
        this.orgnlTxRef = orgnlTxRef;
    }
}