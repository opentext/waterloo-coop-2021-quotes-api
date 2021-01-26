package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class FakeQuote implements QuoteDao {

    private static List<Quote> DB = new ArrayList<>();

    @Override
    public int putQuote(Quote current) {
        DB.add(new Quote(current.getId(), current.getText(), current.getDate()));
        return 1;
    }
}
