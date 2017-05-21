package com.example.ivan.testtransactions.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 5/21/2017.
 */

public class TransactionDetails {
    @SerializedName("contraAccount")
    private AccountDetail contraAccount;

    public TransactionDetails(AccountDetail contraAccount) {
        this.contraAccount = contraAccount;
    }

    public AccountDetail getContraAccount() {
        return contraAccount;
    }

    public void setContraAccount(AccountDetail contraAccount) {
        this.contraAccount = contraAccount;
    }
}
