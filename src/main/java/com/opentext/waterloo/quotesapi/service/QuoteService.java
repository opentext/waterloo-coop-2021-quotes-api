package com.opentext.waterloo.quotesapi.service;


import com.opentext.waterloo.quotesapi.Dao.QuoteDao;
import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteService {

    private final QuoteDao quoteDao;

    @Autowired
    public QuoteService (@Qualifier ("fakeDao") QuoteDao quoteDao){
        this.quoteDao = quoteDao;
    }

    public int addQuote(Quote quote){
        return quoteDao.putQuote(quote);
    }

    public List<Quote> quotes(){
        return List.of(
                new Quote(
                        UUID.randomUUID(),
                        "hello",
                        "2020"
                )
        );
    }

//    public Optional<Quote> getQuoteByDate (String date){ //placeholder date, subject to change
//        return quoteDao.selectQuoteByDate(date);
//    }




}
