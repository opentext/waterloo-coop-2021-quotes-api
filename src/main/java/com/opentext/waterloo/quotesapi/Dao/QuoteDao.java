package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.Optional;

public interface QuoteDao {

    int putQuote(Quote quote);

//    Optional<Quote> getQuoteByDate(String Date);

    Optional<Quote> selectQuoteByDate(String date);

    int incrementLike(boolean like, Quote quote);
}
