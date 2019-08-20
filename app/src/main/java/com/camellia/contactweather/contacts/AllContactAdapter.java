package com.camellia.contactweather.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.camellia.contactweather.R;
import com.camellia.contactweather.main.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class AllContactAdapter extends RecyclerView.Adapter<AllContactViewHolder> implements Filterable {

    private List<AllContactData> allContactList;
    private List<AllContactData> contactListFiltered;
    private Context mContext;
    private final OnItemClickListener listener;
    private final EmptyStateListener emptyStateListener;

    public AllContactAdapter(List<AllContactData> allContactList, Context mContext, OnItemClickListener listener, EmptyStateListener emptyStateListener) {
        this.allContactList = allContactList;
        this.mContext = mContext;
        this.listener = listener;
        this.contactListFiltered = allContactList;
        this.emptyStateListener = emptyStateListener;
    }

    @NonNull
    @Override
    public AllContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.all_contact_list_row, null);
        return new AllContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllContactViewHolder holder, int position) {
//        holder.bind(allContactList.get(position), listener, position);
        holder.bind(contactListFiltered.get(position), listener, position);
    }

    @Override
    public int getItemCount() {
//        return allContactList.size();
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                if (query.isEmpty()) {
                    contactListFiltered = allContactList;
                } else {
                    List<AllContactData> filteredList = new ArrayList<>();
                    for (AllContactData row : allContactList) {
                        if (row.getDisplayName().toLowerCase().contains(query.toLowerCase()) || row.getPhoneNumber().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    contactListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                //noinspection unchecked
                contactListFiltered = (List<AllContactData>) filterResults.values;
                notifyDataSetChanged();
                emptyStateListener.onShowEmptyState(getItemCount() == 0);
            }
        };
    }

    public List<AllContactData> getContactListFiltered() {
        return contactListFiltered;
    }
}

