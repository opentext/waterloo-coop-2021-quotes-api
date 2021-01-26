package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.List;
import java.util.Optional;

public interface QuotesDao {

    int putQuote(Quote quote);

    Optional<Quote> getQuoteByDate(String Date);

}
