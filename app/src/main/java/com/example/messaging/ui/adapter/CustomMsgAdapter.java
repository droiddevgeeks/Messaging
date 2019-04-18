package com.example.messaging.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.messaging.api.model.Message;
import com.example.messaging.databinding.MessagesRowBinding;

import java.util.List;

public class CustomMsgAdapter extends RecyclerView.Adapter<CustomMsgAdapter.ViewHolder> {

    private List<Message> messageList;

    public CustomMsgAdapter(List<Message> list) {
        this.messageList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(MessagesRowBinding.inflate(inflater, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Message message = messageList.get(i);
        viewHolder.binding.tvName.setText(message.getTo());

        String time = message.getDateCreated();
        time = time.substring(0,17);
        viewHolder.binding.tvTime.setText(time);

        String messageText  = message.getBody();
        String otp = messageText.substring(messageText.length()-6, messageText.length());
        viewHolder.binding.tvBody.setText(otp);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void swapData(final List<Message> list) {
        if (!this.messageList.isEmpty()) {
            this.messageList.clear();
        }
        this.messageList.addAll(list);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private MessagesRowBinding binding;

        ViewHolder(MessagesRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
