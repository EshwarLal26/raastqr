package com.example.server.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class NameOnlyParty {

    @XmlElement(name = "Nm")
    private String nm;

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }
}
