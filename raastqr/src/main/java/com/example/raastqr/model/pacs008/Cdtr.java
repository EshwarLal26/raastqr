package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Cdtr {

    @XmlElement(name = "Nm")
    private String nm;

    @XmlElement(name = "PstlAdr")
    private PstlAdr pstlAdr;

    @XmlElement(name = "Id")
    private PartyId id;

    @XmlElement(name = "CtryOfRes")
    private String ctryOfRes;

    @XmlElement(name = "CtctDtls")
    private CtctDtls ctctDtls;

    public void setNm(String nm) { this.nm = nm; }
    public void setPstlAdr(PstlAdr pstlAdr) { this.pstlAdr = pstlAdr; }
    public void setId(PartyId id) { this.id = id; }
    public void setCtryOfRes(String ctryOfRes) { this.ctryOfRes = ctryOfRes; }
    public void setCtctDtls(CtctDtls ctctDtls) { this.ctctDtls = ctctDtls; }
}