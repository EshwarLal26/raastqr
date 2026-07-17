package com.example.raastqr.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class AppHdr {

    @XmlElement(name = "Fr")
    private Party fr;

    @XmlElement(name = "To")
    private Party to;

    @XmlElement(name = "BizMsgIdr")
    private String bizMsgIdr;

    @XmlElement(name = "MsgDefIdr")
    private String msgDefIdr;

    @XmlElement(name = "BizSvc")
    private String bizSvc;

    @XmlElement(name = "CreDt")
    private String creDt;

    public Party getFr() { return fr; }
    public void setFr(Party fr) { this.fr = fr; }

    public Party getTo() { return to; }
    public void setTo(Party to) { this.to = to; }

    public String getBizMsgIdr() { return bizMsgIdr; }
    public void setBizMsgIdr(String bizMsgIdr) { this.bizMsgIdr = bizMsgIdr; }

    public String getMsgDefIdr() { return msgDefIdr; }
    public void setMsgDefIdr(String msgDefIdr) { this.msgDefIdr = msgDefIdr; }

    public String getBizSvc() { return bizSvc; }
    public void setBizSvc(String bizSvc) { this.bizSvc = bizSvc; }

    public String getCreDt() { return creDt; }
    public void setCreDt(String creDt) { this.creDt = creDt; }
}