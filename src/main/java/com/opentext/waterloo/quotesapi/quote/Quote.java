package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.reaction.Reaction;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quote")
public class Quote {

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
    private java.util.UUID quoteUuid;
    private String text;
    private Date date; // ISO date
    private int likes;
    private int dislikes;

    @OneToMany(mappedBy = "quote")
    private List<Reaction> reactions;

    public Quote() {}

    public Quote(String text, Date date, int likes, int dislikes) {
        this.quoteUuid = UUID.randomUUID();
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

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
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
