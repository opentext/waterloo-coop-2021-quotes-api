package com.opentext.waterloo.quotesapi.reaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opentext.waterloo.quotesapi.quote.Quote;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class Reaction {
    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    @Column(name = "ID", updatable = false, nullable = false)
//    @ColumnDefault("random_uuid()")
//    @Type(type = "uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type ="org.hibernate.type.PostgresUUIDType")
    private UUID id;
    @JsonProperty
    private boolean isLike;
    private Date date;
    private String address;

    @ManyToOne
    @JsonIgnore
    private Quote quote;

    public Reaction() {}

    public Reaction(Quote quote, boolean like, String address) {
        this.quote = quote;
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

    public UUID getId() {
        return id;
    }

    public UUID getQuoteId(){
        return quote.getId();
    }

    public Quote getQuote() {
        return quote;
    }
}
