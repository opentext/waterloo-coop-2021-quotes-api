package com.opentext.waterloo.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    public Quote getQuoteByUUID(UUID uuid) {
        Optional<Quote> quote = quoteRepository.findById(uuid);
        return quote.orElseGet(Quote::new);
    }

    public Quote getQuoteByDate(Date date) {
        return Objects.requireNonNullElseGet(
                quoteRepository.findQuoteByDate(date),
                Quote::new);
    }

    public void addQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }
}
