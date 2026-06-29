package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

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

    public void setFr(Party fr) { this.fr = fr; }
    public void setTo(Party to) { this.to = to; }
    public void setBizMsgIdr(String bizMsgIdr) { this.bizMsgIdr = bizMsgIdr; }
    public void setMsgDefIdr(String msgDefIdr) { this.msgDefIdr = msgDefIdr; }
    public void setBizSvc(String bizSvc) { this.bizSvc = bizSvc; }
    public void setCreDt(String creDt) { this.creDt = creDt; }
}