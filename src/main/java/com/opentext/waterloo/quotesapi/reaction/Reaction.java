package com.opentext.waterloo.quotesapi.reaction;

import com.opentext.waterloo.quotesapi.quote.Quote;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Type(type="org.hibernate.type.PostgresUUIDType")
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

    public Quote getQuote() {
        return quote;
    }
}
