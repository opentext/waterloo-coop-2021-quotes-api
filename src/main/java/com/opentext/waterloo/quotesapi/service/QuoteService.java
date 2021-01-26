package com.opentext.waterloo.quotesapi.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    private final QuoteDao quoteDao;

    public QuoteService (@Qualifier ("postgres") QuoteDao quoteDao){
        this.quoteDao = quoteDao;
    }
}
