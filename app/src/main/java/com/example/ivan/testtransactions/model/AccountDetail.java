package com.example.ivan.testtransactions.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 5/21/2017.
 */

public class AccountDetail {
    @SerializedName("accountName")
    private String accountName;
    @SerializedName("accountNumber")
    private String accountNumber;
    @SerializedName("bankCode")
    private String bankCode;

    public AccountDetail(String accountName, String accountNumber, String bankCode) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
