package com.example.ivan.testtransactions.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.testtransactions.R;
import com.example.ivan.testtransactions.rest.ApiClient;
import com.example.ivan.testtransactions.rest.ApiInterface;

/**
 * Created by Ivan on 5/21/2017.
 */

public class LoadingActivity extends AppCompatActivity {

    protected static final String TAG = LoadingActivity.class.getSimpleName();
    protected ApiInterface mApiService;
    private View mLoadingScreen;
    private View mDataScreen;
    private View mErrorScreen;
    private TextView mErrorText;
    private ImageView mReload;
    protected int mTitleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mTitleId);
        setSupportActionBar(toolbar);

        mLoadingScreen = findViewById(R.id.load);
        mDataScreen = findViewById(R.id.data);
        mErrorScreen = findViewById(R.id.error);
        mErrorText = (TextView) findViewById(R.id.error_text);
        mReload = (ImageView) findViewById(R.id.reload_button);
        mApiService = ApiClient.getClient().create(ApiInterface.class);
    }

    protected void showLoading(){
        mLoadingScreen.setVisibility(View.VISIBLE);
        mDataScreen.setVisibility(View.GONE);
        mErrorScreen.setVisibility(View.GONE);
    }

    protected void showError(String error, final OnReloadData listener){
        mLoadingScreen.setVisibility(View.GONE);
        mDataScreen.setVisibility(View.GONE);
        mErrorScreen.setVisibility(View.VISIBLE);
        mErrorText.setText(String.format("%s%s", getText(R.string.error), error));
        mReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDataReload();
            }
        });
    }

    protected void showData(){
        mLoadingScreen.setVisibility(View.GONE);
        mDataScreen.setVisibility(View.VISIBLE);
        mErrorScreen.setVisibility(View.GONE);
    }


    protected interface OnReloadData{
        public void onDataReload();
    }
}
