package com.example.ivan.testtransactions.rest;

import com.example.ivan.testtransactions.model.TransactionDetails;
import com.example.ivan.testtransactions.model.TransactionsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ivan on 5/21/2017.
 */

public interface ApiInterface {
    @GET("transactions")
    Call<TransactionsList> getTransactionsList();

    @GET("transactions/{transaction_id}")
    Call<TransactionDetails> getTransactionDetails(@Path("transaction_id") int id);
}
