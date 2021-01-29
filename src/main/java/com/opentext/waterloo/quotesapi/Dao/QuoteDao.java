package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.List;
import java.util.UUID;

public interface QuoteDao {

    int putQuote(Quote quote);

//    Optional<Quote> getQuoteByDate(String Date);

    Quote selectQuoteByDate(String date);

    void incrementLike(String like, String date);

    List<Quote> allQuotes();

    void addReaction(boolean likes, UUID id);
}
