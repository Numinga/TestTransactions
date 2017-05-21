package com.example.ivan.testtransactions.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ivan.testtransactions.R;
import com.example.ivan.testtransactions.model.Transaction;

import java.util.List;

/**
 * Created by Ivan on 5/21/2017.
 */

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {

    private List<Transaction> mTransactionsList;
    private OnOpenTransaction mListener;
    private int mRow;
    private Context mContext;

    public TransactionsAdapter(List<Transaction> mTransactionsList, int mRow, Context mContext, OnOpenTransaction mListener) {
        this.mTransactionsList = mTransactionsList;
        this.mRow = mRow;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    public void setTransactionsList(List<Transaction> list){
        this.mTransactionsList = list;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRow, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, final int position) {
        holder.mAmount.setText(mTransactionsList.get(position).getAmountInAccountCurrency() + " " + mContext.getString(R.string.currency_code));
        holder.mDirection.setText(mTransactionsList.get(position).getDirection());
        if(mTransactionsList.get(position).getDirection().equals(Transaction.INCOMING)){
            holder.mDirectionImage.setImageResource(R.drawable.right);
        } else {
            holder.mDirectionImage.setImageResource(R.drawable.left);
        }
        final Transaction trans = mTransactionsList.get(position);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onOpenTransaction(trans);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTransactionsList.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mLayout;
        TextView mAmount;
        TextView mDirection;
        ImageView mDirectionImage;

        TransactionViewHolder(View view) {
            super(view);
            mLayout = (RelativeLayout) view.findViewById(R.id.transaction_details);
            mAmount = (TextView) view.findViewById(R.id.price);
            mDirection = (TextView) view.findViewById(R.id.direction);
            mDirectionImage = (ImageView) view.findViewById(R.id.icon);
        }
    }


    public interface OnOpenTransaction{
        void onOpenTransaction(Transaction trans);
    }
}
