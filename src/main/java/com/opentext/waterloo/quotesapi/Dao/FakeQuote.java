package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakeQuote implements QuoteDao {

    private static List<Quote> DB = new ArrayList<>();

    @Override
    public int putQuote(Quote quote) {
        DB.add(new Quote(quote.getId(), quote.getText(), quote.getDate()));
        return 1;
    }

    @Override
    public Quote selectQuoteByDate(String date) {
        return new Quote(UUID.randomUUID(), "no", date);
    }

    @Override
    public void incrementLike(boolean like, String date) {
        return;
    }


    @Override
    public List<Quote> allQuotes() {
        return null;
    }

}
