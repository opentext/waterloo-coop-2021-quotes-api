package com.opentext.waterloo.quotesapi.service;


import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteService {
    private final QuoteDao quoteDao;

    public QuoteService (@Qualifier ("postgres") QuoteDao quoteDao){
        this.quoteDao = quoteDao;
    }

    public int addQuote(Quote quote){
        return personDao.insertQuote(quote);
    }

    public Optional<Quote> getQuoteByDate (String date){ //placeholder date, subject to change
        return quoteDao.selectQuoteById(date);
    }




}
