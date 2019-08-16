package com.camellia.contactweather.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.camellia.contactweather.R;
import com.camellia.contactweather.main.OnItemClickListener;

public class AllContactViewHolder extends RecyclerView.ViewHolder {

    public TextView contactName;
    public TextView contactPhoneNumber;
    public ImageView avatar;

    public AllContactViewHolder(View itemView) {
        super(itemView);
        contactName = itemView.findViewById(R.id.displayName);
        contactPhoneNumber = itemView.findViewById(R.id.phoneNumber);
        avatar = itemView.findViewById(R.id.avatar);
    }

    public void bind(AllContactData item, final OnItemClickListener listener, final int position) {
        contactName.setText(item.getDisplayName());
        contactPhoneNumber.setText(item.getPhoneNumber());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(position);
            }
        });

        Glide.with(avatar)
                .load(item.getAvatar())
                .placeholder(R.drawable.ic_default_contact)
                .apply(RequestOptions.circleCropTransform())
                .into(avatar);

    }

}
