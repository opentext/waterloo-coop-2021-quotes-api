package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.Optional;

public interface QuoteDao {
    int putQuote(String date, Quote quote);

    default int putQuote(Quote quote) {
        String date = "Placeholder"; //Placeholder date
        return putQuote(date, quote);
    }

    Optional<Quote> selectQuoteByDate(String date);
}
