package com.opentext.waterloo.quotesapi.service;


import com.opentext.waterloo.quotesapi.Dao.QuotesDao;
import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteService {
    private final QuotesDao quoteDao;

    public QuoteService (@Qualifier ("postgres") QuotesDao quoteDao){
        this.quoteDao = quoteDao;
    }

    public int addQuote(Quote quote){
        return quoteDao.putQuote(quote);
    }

    public Optional<Quote> getQuoteByDate (String date){ //placeholder date, subject to change
        return quoteDao.selectQuoteByDate(date);
    }




}
