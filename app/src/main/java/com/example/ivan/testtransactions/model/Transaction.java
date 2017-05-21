package com.example.ivan.testtransactions.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 5/21/2017.
 */

public class Transaction {

    public static final String INCOMING = "INCOMING";
    public static final String OUTGOING = "OUTGOING";

    @SerializedName("id")
    private int id;
    @SerializedName("amountInAccountCurrency")
    private int amountInAccountCurrency;
    @SerializedName("direction")
    private String direction;

    public Transaction(int id, int amountInAccountCurrency, String direction) {
        this.id = id;
        this.amountInAccountCurrency = amountInAccountCurrency;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmountInAccountCurrency() {
        return amountInAccountCurrency;
    }

    public void setAmountInAccountCurrency(int amountInAccountCurrency) {
        this.amountInAccountCurrency = amountInAccountCurrency;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
