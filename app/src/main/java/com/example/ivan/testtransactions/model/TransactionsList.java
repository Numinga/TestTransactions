package com.example.ivan.testtransactions.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ivan on 5/21/2017.
 */

public class TransactionsList {
    @SerializedName("items")
    private List<Transaction> items;

    public TransactionsList(List<Transaction> items) {
        this.items = items;
    }

    public List<Transaction> getItems() {
        return items;
    }

    public void setItems(List<Transaction> items) {
        this.items = items;
    }
}
