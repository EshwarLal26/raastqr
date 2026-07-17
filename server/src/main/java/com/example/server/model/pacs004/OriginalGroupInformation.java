package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OriginalGroupInformation {

    @XmlElement(name = "OrgnlMsgId")
    private String orgnlMsgId;

    @XmlElement(name = "OrgnlMsgNmId")
    private String orgnlMsgNmId;

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
}