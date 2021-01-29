package com.opentext.waterloo.quotesapi.reaction;

import com.opentext.waterloo.quotesapi.quote.Quote;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class Reaction {

    @Id
    private final UUID id;
    private final boolean like;
    private final Date date;
    private final String address;

    @ManyToOne
    private final Quote quote;

    public Reaction(boolean like, Date date, String address, Quote quote) {
        this.like = like;
        this.date = date;
        this.address = address;
        this.quote = quote;
        this.id = UUID.randomUUID();
    }

    public Reaction(UUID id, boolean like, String address, Quote quote) {
        this.id = id;
        this.like = like;
        this.quote = quote;
        this.date = new Date();
        this.address = address;
    }

    public Reaction(UUID id, boolean like, Quote quote) {
        this.id = id;
        this.like = like;
        this.quote = quote;
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
