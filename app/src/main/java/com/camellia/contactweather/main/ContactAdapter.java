package com.camellia.contactweather.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camellia.contactweather.R;
import com.camellia.contactweather.contacts.AllContactData;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<AllContactData> myContactList;

    public ContactAdapter(List<AllContactData> myContactList) {
        this.myContactList = myContactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_row, parent, false);
        return new ContactViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.contactName.setText(myContactList.get(position).getDisplayName());
        holder.contactPhoneNumber.setText(myContactList.get(position).getPhoneNumber());
        holder.avatar.setImageResource(myContactList.get(position).getAvatar());

    }

    @Override
    public int getItemCount() {
        return myContactList.size();
    }

}
