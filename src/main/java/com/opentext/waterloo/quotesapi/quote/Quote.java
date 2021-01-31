package com.opentext.waterloo.quotesapi.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opentext.waterloo.quotesapi.reaction.Reaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Quote {

    @Id
    private UUID id;
    private String text;
    private Date date; // ISO date
    private int likes;
    private int dislikes;

    public Quote() {}

    public Quote(UUID id, String text, Date date, int likes, int dislikes) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.likes = likes;
        this.dislikes = likes;
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
