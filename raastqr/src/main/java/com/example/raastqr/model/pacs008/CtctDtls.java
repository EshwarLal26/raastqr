package com.example.raastqr.model.pacs008;

import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CtctDtls {

    @XmlElement(name = "Nm")
    private String nm;

    @XmlElement(name = "MobNb")
    private String mobNb;

    @XmlElement(name = "EmailAdr")
    private String emailAdr;

    @XmlElement(name = "Dept")
    private String dept;

    @XmlElement(name = "Othr")
    private List<Othr> othr;

    public void setNm(String nm) { this.nm = nm; }
    public void setMobNb(String mobNb) { this.mobNb = mobNb; }
    public void setEmailAdr(String emailAdr) { this.emailAdr = emailAdr; }
    public void setDept(String dept) { this.dept = dept; }
    public void setOthr(List<Othr> othr) { this.othr = othr; }
}