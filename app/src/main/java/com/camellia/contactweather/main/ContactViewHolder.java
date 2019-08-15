package com.camellia.contactweather.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.camellia.contactweather.R;

import java.util.Locale;


public class ContactViewHolder extends RecyclerView.ViewHolder {

    public TextView contactName;
    public TextView contactPhoneNumber;
    public ImageView avatar;
    public ImageView txtOptionDigit;
    public TextView txtTemperature;
    public TextView txtLocation;
    public ImageView weatherIcon;

    public ContactViewHolder(View itemView) {
        super(itemView);
        contactName = itemView.findViewById(R.id.displayName);
        contactPhoneNumber = itemView.findViewById(R.id.phoneNumber);
        avatar = itemView.findViewById(R.id.avatar);
        txtOptionDigit = itemView.findViewById(R.id.optionMenuImageView);
        txtTemperature = itemView.findViewById(R.id.txtTemperature);
        txtLocation = itemView.findViewById(R.id.txtCityName);
        weatherIcon = itemView.findViewById(R.id.weatherIcon);
    }

    public void bind(final ContactData item, final OnOptionMenuItemClickListener listener, final int position) {
        contactName.setText(item.getDisplayName());
        contactPhoneNumber.setText(item.getPhoneNumber());
        avatar.setImageResource(R.drawable.ic_launcher_background);

        if(item.getDataModel()!= null){
//            txtLocation.setText(String.format("%s   %s°C", item.getDataModel().getName(), String.format(Locale.getDefault(), "%.1f", item.getDataModel().main.getTemperature() - 273.5)));
            txtLocation.setText(item.getDataModel().getName());
            txtTemperature.setText(String.format("%s°C", String.format(Locale.getDefault(), "%.1f", item.getDataModel().main.getTemperature() - 273.5)));

            String iconCode = item.getDataModel().weather.get(0).getIcon();
            String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
            Glide.with(weatherIcon)
                    .load(iconUrl)
                    .into(weatherIcon);
        }


        txtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOptionMenuItemClicked(txtOptionDigit, position);
            }
        });

    }
}
