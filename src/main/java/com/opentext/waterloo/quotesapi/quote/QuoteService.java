package com.opentext.waterloo.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public void addQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public Quote getQuote(Date date) {
        return quoteRepository.findQuoteByDate(date);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }
}
