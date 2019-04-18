package com.example.messaging.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.messaging.api.model.Contact;
import com.example.messaging.databinding.ContactsRowBinding;
import com.example.messaging.ui.callback.IItemClick;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {

    private List<Contact> contacts;
    private IItemClick listener;

    public CustomListAdapter(List<Contact> list) {
        this.contacts = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(ContactsRowBinding.inflate(inflater, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Contact contact = contacts.get(i);
        viewHolder.binding.tvUser.setText(contact.getFname() + " " + contact.getLname());
        viewHolder.binding.tvContact.setText(contact.getPhone());
        viewHolder.binding.rlRow.setOnClickListener(view -> listener.onItemClick(contact, i));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void swapData(final List<Contact> list) {
        if (!this.contacts.isEmpty()) {
            this.contacts.clear();
        }
        this.contacts.addAll(list);
        notifyDataSetChanged();
    }

    public void addListener(IItemClick listener) {
        this.listener = listener;
    }

    public void removeListener() {
        listener = null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ContactsRowBinding binding;

        ViewHolder(ContactsRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
