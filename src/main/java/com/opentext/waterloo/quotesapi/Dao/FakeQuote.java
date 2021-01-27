package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakeDao")
public class FakeQuote implements QuoteDao {

    private static List<Quote> DB = new ArrayList<>();

    @Override
    public int putQuote(Quote quote) {
        DB.add(new Quote(quote.getId(), quote.getText(), quote.getDate()));
        return 1;
    }

    @Override
    public Optional<Quote> selectQuoteByDate(String date) {
        return DB.stream()
                .filter(quote -> quote.getDate().equals(date))
                .findFirst();
    }

    @Override
    public int incrementLike(boolean like, Quote quote) {
        return 0;
    }

}
