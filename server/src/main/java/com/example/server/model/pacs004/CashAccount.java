package com.example.server.model.pacs004;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class CashAccount {

    @XmlElement(name = "Id")
    private AccountId id;

    public AccountId getId() {
        return id;
    }

    public void setId(AccountId id) {
        this.id = id;
    }
}
