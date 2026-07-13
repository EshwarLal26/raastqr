package com.example.server.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OriginalGroupInfoAndStatus {

    @XmlElement(name = "OrgnlMsgId")
    private String orgnlMsgId;

    @XmlElement(name = "OrgnlMsgNmId")
    private String orgnlMsgNmId;

    @XmlElement(name = "OrgnlCreDtTm")
    private String orgnlCreDtTm;

    @XmlElement(name = "OrgnlNbOfTxs")
    private String orgnlNbOfTxs;

    @XmlElement(name = "GrpSts")
    private String grpSts;

    public String getOrgnlMsgId() {
        return orgnlMsgId;
    }

    public void setOrgnlMsgId(String orgnlMsgId) {
        this.orgnlMsgId = orgnlMsgId;
    }

    public String getOrgnlMsgNmId() {
        return orgnlMsgNmId;
    }

    public void setOrgnlMsgNmId(String orgnlMsgNmId) {
        this.orgnlMsgNmId = orgnlMsgNmId;
    }

    public String getOrgnlCreDtTm() {
        return orgnlCreDtTm;
    }

    public void setOrgnlCreDtTm(String orgnlCreDtTm) {
        this.orgnlCreDtTm = orgnlCreDtTm;
    }

    public String getOrgnlNbOfTxs() {
        return orgnlNbOfTxs;
    }

    public void setOrgnlNbOfTxs(String orgnlNbOfTxs) {
        this.orgnlNbOfTxs = orgnlNbOfTxs;
    }

    public String getGrpSts() {
        return grpSts;
    }

    public void setGrpSts(String grpSts) {
        this.grpSts = grpSts;
    }
}