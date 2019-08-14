package com.camellia.contactweather.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.camellia.contactweather.R;
import com.camellia.contactweather.contacts.AllContactData;
import com.camellia.contactweather.contacts.DataBaseHelper;

import java.util.Locale;


public class ContactViewHolder extends RecyclerView.ViewHolder {

    public TextView contactName;
    public TextView contactPhoneNumber;
    public ImageView avatar;
    public TextView txtOptionDigit;
    public TextView txtWeatherStatus;

    public ContactViewHolder(View itemView) {
        super(itemView);
        contactName = itemView.findViewById(R.id.displayName);
        contactPhoneNumber = itemView.findViewById(R.id.phoneNumber);
        avatar = itemView.findViewById(R.id.avatar);
        txtOptionDigit = itemView.findViewById(R.id.txtOptionDigit);
        txtWeatherStatus = itemView.findViewById(R.id.txtWeatherStatus);
    }

    public void bind(final ContactData item, final OnOptionMenuItemClickListener listener, final int position) {
        contactName.setText(item.getDisplayName());
        contactPhoneNumber.setText(item.getPhoneNumber());
        avatar.setImageResource(item.getAvatar());
//        txtWeatherStatus.setText(String.format("%s\n%s", item.getLatitude(), item.getLongitude()));
        txtWeatherStatus.setText(String.format("%s   %s°C", item.getCity(), String.format(Locale.getDefault(), "%.1f", item.getTemperature() - 273.5)));

        txtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOptionMenuItemClicked(txtOptionDigit, position);
            }
        });

    }
}
