package com.camellia.contactweather.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camellia.contactweather.R;
import com.camellia.contactweather.main.OnItemClickListener;

import java.util.List;

public class AllContactAdapter extends RecyclerView.Adapter<AllContactViewHolder> {

    private List<AllContactData> myContactList;
    private Context mContext;
    private final OnItemClickListener listener;

    public AllContactAdapter(List<AllContactData> myContactList, Context mContext, OnItemClickListener listener) {
        this.myContactList = myContactList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AllContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.all_contact_list_row, null);
        return new AllContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllContactViewHolder holder, int position) {
        holder.bind(myContactList.get(position), listener, position);
    }

    @Override
    public int getItemCount() {
        return myContactList.size();
    }
}
