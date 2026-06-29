package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GrpHdr {
    @XmlElement(name = "MsgId")
    private String msgId;

    @XmlElement(name = "CreDtTm")
    private String creDtTm;

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setCreDtTm(String creDtTm) {
        this.creDtTm = creDtTm;
    }

    public void setNbOfTxs(String nbOfTxs) {
        this.nbOfTxs = nbOfTxs;
    }

    public void setSttlmInf(SttlmInf sttlmInf) {
        this.sttlmInf = sttlmInf;
    }
    @XmlElement(name = "BtchBookg")
private Boolean btchBookg;

public void setBtchBookg(Boolean btchBookg) {
    this.btchBookg = btchBookg;
}

    @XmlElement(name = "NbOfTxs")
    private String nbOfTxs;

    @XmlElement(name = "SttlmInf")
    private SttlmInf sttlmInf;
}