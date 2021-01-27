package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.Optional;

public interface QuoteDao {

    int putQuote(Quote current);

    Optional<Quote> selectQuoteByDate(String date);
}
