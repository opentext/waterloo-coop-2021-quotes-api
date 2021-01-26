package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class FakeQuote implements QuotesDao{

    private static List<Quote> DB = new ArrayList<>();

    @Override
    public int putQuote(Quote quote) {
        DB.add(new Quote(quote.getId(), quote.getText(), quote.getDate()));
        return 1;
    }

    @Override
    public List<Quote> selectAllQuote() {
        return DB;
    }
}
