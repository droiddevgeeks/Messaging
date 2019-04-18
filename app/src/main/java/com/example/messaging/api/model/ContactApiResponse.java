package com.example.messaging.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactApiResponse {

    @SerializedName("contact")
    @Expose
    private List<Contact> contact = null;

    public List<Contact> getContact() {
        return contact;
    }
}