package com.opentext.waterloo.quotesapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Quote {

    private final UUID id;
    private final String text;
    private final String date; // ISO date

    public Quote(@JsonProperty("id") UUID id,
                 @JsonProperty("text") String text,
                 @JsonProperty("date") String date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
