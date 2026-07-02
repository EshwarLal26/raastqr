package com.example.raastqr.model.pacs002;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AppHdr {

    @XmlElement(name = "Fr", namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01")
    private Party fr;

    @XmlElement(name = "To", namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01")
    private Party to;

    @XmlElement(name = "BizMsgIdr", namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01")
    private String bizMsgIdr;

    @XmlElement(name = "MsgDefIdr", namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01")
    private String msgDefIdr;

    @XmlElement(name = "BizSvc", namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01")
    private String bizSvc;

    @XmlElement(name = "CreDt", namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01")
    private String creDt;

    @XmlElement(name = "Sgntr", namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01")
    private SignatureWrapper sgntr;

    public Party getFr() {
        return fr;
    }

    public void setFr(Party fr) {
        this.fr = fr;
    }

    public Party getTo() {
        return to;
    }

    public void setTo(Party to) {
        this.to = to;
    }

    public String getBizMsgIdr() {
        return bizMsgIdr;
    }

    public void setBizMsgIdr(String bizMsgIdr) {
        this.bizMsgIdr = bizMsgIdr;
    }

    public String getMsgDefIdr() {
        return msgDefIdr;
    }

    public void setMsgDefIdr(String msgDefIdr) {
        this.msgDefIdr = msgDefIdr;
    }

    public String getBizSvc() {
        return bizSvc;
    }

    public void setBizSvc(String bizSvc) {
        this.bizSvc = bizSvc;
    }

    public String getCreDt() {
        return creDt;
    }

    public void setCreDt(String creDt) {
        this.creDt = creDt;
    }

    public SignatureWrapper getSgntr() {
        return sgntr;
    }

    public void setSgntr(SignatureWrapper sgntr) {
        this.sgntr = sgntr;
    }
}