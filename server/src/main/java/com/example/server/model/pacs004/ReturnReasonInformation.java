package com.example.server.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReturnReasonInformation {

    @XmlElement(name = "Orgtr")
    private NameOnlyParty orgtr;

    @XmlElement(name = "Rsn")
    private ReturnReason rsn;

    @XmlElement(name = "AddtlInf")
    private String addtlInf;

    public NameOnlyParty getOrgtr() { return orgtr; }
    public void setOrgtr(NameOnlyParty orgtr) { this.orgtr = orgtr; }

    public ReturnReason getRsn() { return rsn; }
    public void setRsn(ReturnReason rsn) { this.rsn = rsn; }

    public String getAddtlInf() { return addtlInf; }
    public void setAddtlInf(String addtlInf) { this.addtlInf = addtlInf; }
}
