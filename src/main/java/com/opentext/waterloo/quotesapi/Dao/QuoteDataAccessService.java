package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class QuoteDataAccessService implements QuoteDao{

    @Override
    public int putQuote(Quote quote) {
        return 0;
    }

    @Override
    public Optional<Quote> getQuoteByDate(String Date) {
        return Optional.empty();
    }

    @Override
    public int insertQuote(Quote quote) {
        return 0;
    }

    @Override
    public Optional<Quote> selectQuoteByDate(String date) {
        return Optional.empty();
    }

    public List<Quote> selectAllQuote() {
        return List.of(new Quote(UUID.randomUUID(), "This is a placeholder", "Date placeholder"));
    }
}