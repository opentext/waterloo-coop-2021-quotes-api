package com.opentext.waterloo.quotesapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.xml.bind.v2.TODO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table
public class Quote {
    @Id
    @SequenceGenerator(
            name = "quote_sequence",
            sequenceName = "quote_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quote_sequence"
    )

    private final UUID id;
    private final String text;
    private final String date; // ISO date
    private int likes;
    private int dislikes;

    private ArrayList<Reaction> reactions = new ArrayList<>();

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
