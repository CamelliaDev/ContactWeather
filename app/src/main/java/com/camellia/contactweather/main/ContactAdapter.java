package com.camellia.contactweather.main;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.camellia.contactweather.R;
import com.camellia.contactweather.contacts.AllContactData;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<AllContactData> myContactList;
    private Context mContext;

    public ContactAdapter(List<AllContactData> myContactList, Context mContext) {
        this.myContactList = myContactList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_row, parent, false);
        return new ContactViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactViewHolder holder, int position) {
        holder.contactName.setText(myContactList.get(position).getDisplayName());
        holder.contactPhoneNumber.setText(myContactList.get(position).getPhoneNumber());
        holder.avatar.setImageResource(myContactList.get(position).getAvatar());

        holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display option menu

                PopupMenu popupMenu = new PopupMenu(mContext, holder.txtOptionDigit);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_item_delete:
                                Toast.makeText(mContext, "delete", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_item_changeLocation:
                                Toast.makeText(mContext, "change location", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return myContactList.size();
    }

}
