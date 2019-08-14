package com.camellia.contactweather.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.camellia.contactweather.R;
import com.camellia.contactweather.contacts.AllContactData;
import com.camellia.contactweather.contacts.DataBaseHelper;

import java.util.List;
import java.util.Locale;


public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<ContactData> myContactList;
    private Context mContext;
    private OnOptionMenuItemClickListener listener;

    public ContactAdapter(List<ContactData> myContactList, Context mContext, OnOptionMenuItemClickListener listener) {
        this.myContactList = myContactList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_row, parent, false);
        return new ContactViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactViewHolder holder, int position) {
        holder.bind(myContactList.get(position), listener, position);
    }

    @Override
    public int getItemCount() {
        return myContactList.size();
    }


}
