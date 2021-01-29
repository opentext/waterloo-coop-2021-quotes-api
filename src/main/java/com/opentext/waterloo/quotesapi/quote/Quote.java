package com.opentext.waterloo.quotesapi.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opentext.waterloo.quotesapi.reaction.Reaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Quote {

    @Id
    private final UUID id;
    private final String text;
    private final String date; // ISO date
    private int likes;
    private int dislikes;

    public Quote(@JsonProperty("id") UUID id,
                 @JsonProperty("text") String text,
                 @JsonProperty("date") String date) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.likes = 0;
        this.dislikes = 0;
    }

    public boolean addReaction(boolean isLike) {
        if (uniqueAddress()) {
            reactions.add(new Reaction(isLike));
            if (isLike) {
                likes ++;
            }
            else {
                dislikes ++;
            }
            return true;
        }
        return false;
    }

    private boolean uniqueAddress() {
        // TODO: Add code to get ip address, then check if unique
        return true;
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

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }
}
