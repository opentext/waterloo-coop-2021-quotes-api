package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.List;

public interface QuotesDao {

    int putQuote(Quote quote);

    List<Quote> selectAllQuote();

}
