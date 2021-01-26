package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.Optional;

public interface QuoteDao {

    int putQuote(Quote current);

    Optional<Quote> getQuoteByDate(String Date);

    int insertQuote(Quote quote);

    Optional<Quote> selectQuoteByDate(String date);
}
