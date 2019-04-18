package com.example.messaging.ui.callback;

import com.example.messaging.api.model.Contact;

public interface IItemClick {
    void onItemClick(Contact contact, int position);
}
