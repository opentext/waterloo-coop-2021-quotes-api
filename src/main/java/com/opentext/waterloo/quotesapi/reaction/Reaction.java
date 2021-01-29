package com.opentext.waterloo.quotesapi.reaction;

import com.opentext.waterloo.quotesapi.quote.Quote;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
public class Reaction {

    @Id
    private final UUID id;
    private final boolean like;
    private final Date date;
    private final String address;

    @ManyToOne
    private final Quote quote;

    public Reaction(boolean like, Date date, String address) {
        this.like = like;
        this.date = date;
        this.address = address;
    }

    public Reaction(boolean like, String address) {
        this.like = like;
        this.date = new Date();
        this.address = address;
    }

    public Reaction(boolean like) {
        this.like = like;
        this.date = new Date();
        this.address = "";
    }

    public boolean getLike() {
        return like;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }
}
