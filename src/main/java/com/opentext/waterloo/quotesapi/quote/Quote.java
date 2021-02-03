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

    public Quote(UUID quoteUuid, String text, Date date, int likes, int dislikes) {
        this.quoteUuid = quoteUuid;
        this.text = text;
        this.date = QuoteService.roundDate(date);
        this.likes = likes;
        this.dislikes = dislikes;
    }

    // Only used for testing purposes
    public void roundDate() {
        this.date = QuoteService.roundDate(date);
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
