package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlElement;

public class FIToFICstmrCdtTrf {
    @XmlElement(name = "GrpHdr")
    private GrpHdr grpHdr;

    @XmlElement(name = "CdtTrfTxInf")
    private CdtTrfTxInf cdtTrfTxInf;


    public void setGrpHdr(GrpHdr grpHdr) {
    this.grpHdr = grpHdr;
}

public void setCdtTrfTxInf(CdtTrfTxInf cdtTrfTxInf) {
    this.cdtTrfTxInf = cdtTrfTxInf;
}
}
