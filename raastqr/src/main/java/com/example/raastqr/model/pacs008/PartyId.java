package com.example.raastqr.model.pacs008;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PartyId {
    @XmlElement(name = "OrgId")
    private OrgId orgId;

    public void setOrgId(OrgId orgId) {
        this.orgId = orgId;
    }
}