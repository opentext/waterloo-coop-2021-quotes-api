package com.opentext.waterloo.quotesapi.model;

import java.util.UUID;

public class Quote {

    private final UUID id;
    private final String text;
    private final String date; // ISO date

    public Quote(UUID id, String text, String date) {
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
