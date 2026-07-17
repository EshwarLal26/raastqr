package com.example.server.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class FIToFIPmtRtr {

    @XmlElement(name = "GrpHdr")
    private GroupHeader grpHdr;

    @XmlElement(name = "TxInf")
    private TransactionInformation txInf;

    public GroupHeader getGrpHdr() {
        return grpHdr;
    }

    public void setGrpHdr(GroupHeader grpHdr) {
        this.grpHdr = grpHdr;
    }

    public TransactionInformation getTxInf() {
        return txInf;
    }

    public void setTxInf(TransactionInformation txInf) {
        this.txInf = txInf;
    }
}
