package com.example.ivan.testtransactions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.ivan.testtransactions.R;
import com.example.ivan.testtransactions.adapter.TransactionsAdapter;
import com.example.ivan.testtransactions.model.Transaction;
import com.example.ivan.testtransactions.model.TransactionsList;
import com.example.ivan.testtransactions.rest.ApiClient;
import com.example.ivan.testtransactions.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsListActivity extends LoadingActivity implements LoadingActivity.OnReloadData, TransactionsAdapter.OnOpenTransaction{

    private RecyclerView mRecyclerView;
    private List<Transaction> mTransactions;
    private TransactionsAdapter mAdapter;
    private DividerItemDecoration mDividerItemDecoration;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_transactions_list);
        super.onCreate(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        mApiService = ApiClient.getClient().create(ApiInterface.class);
        getTransactions();
    }

    private void getTransactions(){
        Call<TransactionsList> call = mApiService.getTransactionsList();
        call.enqueue(new Callback<TransactionsList>() {
            @Override
            public void onResponse(Call<TransactionsList>call, Response<TransactionsList> response) {
                mTransactions = response.body().getItems();
                Log.d(TAG, "Number of transactions received: " + mTransactions.size());
                for (Transaction t:mTransactions) {
                    Log.d(TAG, "Transaction: " + t.getId() + " " + t.getDirection() + " " + t.getAmountInAccountCurrency());
                }
                showData();

                mAdapter = new TransactionsAdapter(mTransactions, R.layout.transaction_item, getApplicationContext(), TransactionsListActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<TransactionsList>call, Throwable t) {
                showError(t.getMessage(), TransactionsListActivity.this);
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.all:
                showSelected(null);
                return true;
            case R.id.incoming:
                showSelected(Transaction.INCOMING);
                return true;
            case R.id.outgoing:
                showSelected(Transaction.OUTGOING);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDataReload() {
        showLoading();
        getTransactions();
    }

    @Override
    public void onOpenTransaction(Transaction trans) {
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra(TransactionActivity.KEY_ID, trans.getId());
        intent.putExtra(TransactionActivity.KEY_PRICE, trans.getAmountInAccountCurrency());
        intent.putExtra(TransactionActivity.KEY_DIRECTION, trans.getDirection());
        startActivity(intent);
    }

    private void showSelected(String match){
        if(match == null){
            mAdapter.setTransactionsList(mTransactions);
            mAdapter.notifyDataSetChanged();
        } else {
            List<Transaction> tempList = new ArrayList<>();
            for (Transaction t: mTransactions) {
                if(t.getDirection().equals(match)){
                    tempList.add(t);
                }
            }
            mAdapter.setTransactionsList(tempList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
