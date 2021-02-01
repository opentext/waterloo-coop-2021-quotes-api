package com.opentext.waterloo.quotesapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.xml.bind.v2.TODO;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

//@Entity
//@Table
public class Quote {
//    @Id
//    @SequenceGenerator(
//            name = "quote_sequence",
//            sequenceName = "quote_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "quote_sequence"
//    )
    //@Id
    //@GeneratedValue (strategy = GenerationType.AUTO)
    private final UUID id;
    private final String text;
    private final String date; // ISO date
    private int likes;
    private int dislikes;

    private ArrayList<Reaction> reactions = new ArrayList<>();

    public Quote(@JsonProperty("id") UUID id,
                 @JsonProperty("text") String text,
                 @JsonProperty("date") String date,
                 @JsonProperty("likes") int likes,
                 @JsonProperty("dislikes") int dislikes,
                 @JsonProperty("reactions") ArrayList<Reaction> reactions) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
        this.reactions = reactions;
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

    public ArrayList<Reaction> reactions() {
        return reactions;
    }
}
