package com.example.messaging.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("date_created")
    @Expose
    private String dateCreated;

    @SerializedName("date_sent")
    @Expose
    private String dateSent;

    @SerializedName("to")
    @Expose
    private String to;

    @SerializedName("from")
    @Expose
    private String from;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("status")
    @Expose
    private String status;

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateSent() {
        return dateSent;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getBody() {
        return body;
    }

    public String getStatus() {
        return status;
    }

}