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
    private UUID id;
    private boolean isLike;
    private Date date;
    private String address;

    @ManyToOne
    private Quote quote;

    public Reaction() {}

    public Reaction(Quote quote, boolean like, String address) {
        this.quote = quote;
        this.id = UUID.randomUUID();
        this.isLike = like;
        this.date = new Date();
        this.address = address;
    }

    public boolean getLike() {
        return isLike;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }
}
