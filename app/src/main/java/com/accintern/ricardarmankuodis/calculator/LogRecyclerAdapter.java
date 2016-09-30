package com.accintern.ricardarmankuodis.calculator;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ricard.arman.kuodis on 9/30/2016.
 */

public class LogRecyclerAdapter extends RecyclerView.Adapter<LogRecyclerAdapter.ViewHolder> {

    private static final String TAG =LogRecyclerAdapter.class.getSimpleName();
    private final List<String> mDataSet;

    public LogRecyclerAdapter(List<String> dataSet){
        mDataSet=dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG,"onCeateViewHolder()");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder()");
        holder.idTextView.setText(position+").");
        holder.dataTextView.setText(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dataTextView;
        public TextView idTextView;

        public ViewHolder(View v) {
            super(v);
            dataTextView = (TextView) v.findViewById(R.id.dataTextView);
            idTextView = (TextView) v.findViewById(R.id.idTextView);
        }


    }
}
