package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.List;

public interface QuoteDao {

    int putQuote(Quote quote);

//    Optional<Quote> getQuoteByDate(String Date);

    Quote selectQuoteByDate(String date);

    void incrementLike(boolean like, String date);

    List<Quote> allQuotes();
}
