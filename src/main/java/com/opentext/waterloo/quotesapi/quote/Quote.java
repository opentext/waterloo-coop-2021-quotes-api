package com.opentext.waterloo.quotesapi.quote;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
public class Quote {

    @Id
    private final UUID id;
    private final String text;
    private final Date date; // ISO date
    private int likes;
    private int dislikes;

    public Quote(@JsonProperty("id") UUID id,
                 @JsonProperty("text") String text,
                 @JsonProperty("date") Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.likes = 0;
        this.dislikes = 0;
    }


    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }
}
