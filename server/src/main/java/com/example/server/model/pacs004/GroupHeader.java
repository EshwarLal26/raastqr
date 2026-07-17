package com.example.server.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class GroupHeader {

    @XmlElement(name = "MsgId")
    private String msgId;

    @XmlElement(name = "CreDtTm")
    private String creDtTm;

    @XmlElement(name = "NbOfTxs")
    private String nbOfTxs;

    @XmlElement(name = "SttlmInf")
    private SettlementInformation sttlmInf;

    public String getMsgId() { return msgId; }
    public void setMsgId(String msgId) { this.msgId = msgId; }

    public String getCreDtTm() { return creDtTm; }
    public void setCreDtTm(String creDtTm) { this.creDtTm = creDtTm; }

    public String getNbOfTxs() { return nbOfTxs; }
    public void setNbOfTxs(String nbOfTxs) { this.nbOfTxs = nbOfTxs; }

    public SettlementInformation getSttlmInf() { return sttlmInf; }
    public void setSttlmInf(SettlementInformation sttlmInf) { this.sttlmInf = sttlmInf; }
}
