package com.opentext.waterloo.quotesapi.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opentext.waterloo.quotesapi.reaction.Reaction;
import org.hibernate.annotations.Type;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "quote")
@EnableScheduling
public class Quote {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @Type(type="org.hibernate.type.PostgresUUIDType")
//    private java.util.UUID quoteUuid;
    private Long id;
    private String text;
    private Date date; // ISO date
    private int likes;
    private int dislikes;


    public Quote() {}

    public Quote(String text, Date date, int likes, int dislikes) {
//        this.id = quoteUuid;
        this.text = text;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public void setText(String text) {
        this.text = text;
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

    public void incrementLikes(boolean like){
        if (like){
            likes++;
        }
        else{
            dislikes++;
        }
    }

}
