package com.example.server.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FIToFIPmtStsRpt {

    @XmlElement(name = "GrpHdr")
    private GroupHeader grpHdr;

    @XmlElement(name = "OrgnlGrpInfAndSts")
    private OriginalGroupInfoAndStatus orgnlGrpInfAndSts;

    @XmlElement(name = "TxInfAndSts")
    private TransactionInfoAndStatus txInfAndSts;

    public GroupHeader getGrpHdr() {
        return grpHdr;
    }

    public void setGrpHdr(GroupHeader grpHdr) {
        this.grpHdr = grpHdr;
    }

    public OriginalGroupInfoAndStatus getOrgnlGrpInfAndSts() {
        return orgnlGrpInfAndSts;
    }

    public void setOrgnlGrpInfAndSts(OriginalGroupInfoAndStatus orgnlGrpInfAndSts) {
        this.orgnlGrpInfAndSts = orgnlGrpInfAndSts;
    }

    public TransactionInfoAndStatus getTxInfAndSts() {
        return txInfAndSts;
    }

    public void setTxInfAndSts(TransactionInfoAndStatus txInfAndSts) {
        this.txInfAndSts = txInfAndSts;
    }
}