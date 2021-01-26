package com.opentext.waterloo.quotesapi.Dao;

import java.util.ArrayList;
import java.util.List;

public class FakeQuote implements QuotesDao{

    private static List<Quote> DB = new ArrayList<>();

    @Override
    public int putQuote(Quote current) {
        DB.add(new Quote(quote.getId(), quote.getText, quote.getDate);
        return 1;
    }
}
