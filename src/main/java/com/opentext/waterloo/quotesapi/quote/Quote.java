package com.opentext.waterloo.quotesapi.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opentext.waterloo.quotesapi.reaction.Reaction;
import org.hibernate.annotations.Type;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private java.util.UUID quoteUuid;
    private String text;
    private Date date; // ISO date
    private int likes;
    private int dislikes;


    public Quote() {}

    public Quote(@JsonProperty("text") String text,
                 @JsonProperty("date") Date date,
                 @JsonProperty UUID quoteUuid) {
        this.quoteUuid = quoteUuid;
        this.text = text;
        this.date = date;
        this.likes = 0;
        this.dislikes = 0;
    }

    private boolean uniqueAddress() {
        // TODO: Add code to get ip address, then check if unique
        return true;
    }

    public UUID getId() {
        return quoteUuid;
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
    public void addLike(){
        likes++;
    }

    public void addDislike(){
        dislikes++;
    }

}
