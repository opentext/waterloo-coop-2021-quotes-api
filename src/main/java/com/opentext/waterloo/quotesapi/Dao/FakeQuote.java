package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeQuote implements QuotesDao{

    private static List<Quote> DB = new ArrayList<>();

    @Override
    public int putQuote(Quote quote) {
        DB.add(new Quote(quote.getId(), quote.getText(), quote.getDate()));
        return 1;
    }

    @Override
    public Optional<Quote> getQuoteByDate(String Date) {
        return DB.stream()
                .filter(quote -> quote.getDate().equals(Date))
                .findFirst();
    }


}
