package com.opentext.waterloo.quotesapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Quote {
    @Id
    @SequenceGenerator(
            name = "quote_sequence",
            sequenceName = "quote_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quote_sequence"
    )

    private final UUID id;
    private final String text;
    private final String date; // ISO date

    public Quote(@JsonProperty("id") UUID id,
                 @JsonProperty("text") String text,
                 @JsonProperty("date") String date) {
        this.id = id;
        this.text = text;
        this.date = date;
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
}
