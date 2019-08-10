package com.camellia.contactweather.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.camellia.contactweather.R;


public class ContactViewHolder extends RecyclerView.ViewHolder{

    public TextView contactName;
    public TextView contactPhoneNumber;
    public ImageView avatar;

    public ContactViewHolder(View itemView) {
        super(itemView);
        contactName = itemView.findViewById(R.id.displayName);
        contactPhoneNumber= itemView.findViewById(R.id.phoneNumber);
        avatar = itemView.findViewById(R.id.avatar);
    }
}
