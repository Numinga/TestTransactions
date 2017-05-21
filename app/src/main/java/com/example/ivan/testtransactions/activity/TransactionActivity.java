package com.example.ivan.testtransactions.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.testtransactions.R;
import com.example.ivan.testtransactions.model.AccountDetail;
import com.example.ivan.testtransactions.model.Transaction;
import com.example.ivan.testtransactions.model.TransactionDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends LoadingActivity implements LoadingActivity.OnReloadData{

    protected static final String KEY_ID = "id";
    protected static final String KEY_PRICE = "price";
    protected static final String KEY_DIRECTION = "direction";
    private ImageView mDirectionImage;
    private TextView mPrice;
    private TextView mDirection;
    private TextView mAccountName;
    private TextView mAccountNumber;
    private TextView mBankCode;
    private int mId;
    private int mPriceNumber;
    private String mDirectionText;
    private AccountDetail mAccountDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_transaction);
        mTitleId = R.string.transaction_details;
        super.onCreate(savedInstanceState);
        mId = getIntent().getIntExtra(KEY_ID, 0);
        mPriceNumber = getIntent().getIntExtra(KEY_PRICE, 0);
        mDirectionText = getIntent().getStringExtra(KEY_DIRECTION);

        mDirectionImage = (ImageView) findViewById(R.id.icon);
        mPrice = (TextView) findViewById(R.id.price);
        mDirection = (TextView) findViewById(R.id.direction);
        mAccountName = (TextView) findViewById(R.id.account_name);
        mAccountNumber = (TextView) findViewById(R.id.account_number);
        mBankCode = (TextView) findViewById(R.id.bank_code);
        getTransaction();
    }

    private void getTransaction(){
        Call<TransactionDetails> call = mApiService.getTransactionDetails(mId);
        call.enqueue(new Callback<TransactionDetails>() {
            @Override
            public void onResponse(Call<TransactionDetails>call, Response<TransactionDetails> response) {
                if(response.body() == null || response.body().getContraAccount() == null){
                    showError("No data received for transaction.", TransactionActivity.this);
                } else {
                    mAccountDetails = response.body().getContraAccount();
                    Log.d(TAG, mAccountDetails.getAccountName());
                    updateData();
                }
            }

            @Override
            public void onFailure(Call<TransactionDetails>call, Throwable t) {
                showError(t.getMessage(), TransactionActivity.this);
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onDataReload() {
        showLoading();
        getTransaction();
    }

    private void updateData(){
        showData();
        mAccountName.setText(mAccountDetails.getAccountName());
        mAccountNumber.setText(mAccountDetails.getAccountNumber());
        mBankCode.setText(mAccountDetails.getBankCode());
        mDirection.setText(mDirectionText);
        mPrice.setText(mPriceNumber + " " + getText(R.string.currency_code));
        if(mDirectionText.equals(Transaction.INCOMING)){
            mDirectionImage.setImageResource(R.drawable.right);
        } else {
            mDirectionImage.setImageResource(R.drawable.left);
        }
    }
}
