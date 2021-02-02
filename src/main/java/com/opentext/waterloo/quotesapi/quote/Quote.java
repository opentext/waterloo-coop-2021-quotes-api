package com.opentext.waterloo.quotesapi.quote;

import org.hibernate.annotations.Type;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "quote")
@EnableScheduling
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

    public Quote(UUID quoteUuid, String text, Date date) {
        this.quoteUuid = quoteUuid;
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

    public void incrementLikes(boolean like){
        if (like){
            likes++;
        }
        else{
            dislikes++;
        }
    }

}
