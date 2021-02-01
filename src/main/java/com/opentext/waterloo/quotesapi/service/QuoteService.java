package com.opentext.waterloo.quotesapi.service;


import com.opentext.waterloo.quotesapi.Dao.QuoteDao;
import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    private final QuoteDao quoteDao;

    @Autowired
    public QuoteService (@Qualifier ("postgres") QuoteDao quoteDao){ //change qualifier to postgres later
        this.quoteDao = quoteDao;
    }

    public int addQuote(Quote quote){
        return quoteDao.putQuote(quote);
    }

    public List<Quote> quotes(){ //idk how necessary this is
        return quoteDao.allQuotes();
    }


    public Quote getQuoteByDate (String date){ //placeholder date, subject to change
        return quoteDao.selectQuoteByDate(date);
    }

    public void incrementLikes(String like, String date, String address){
        quoteDao.incrementLike(like, date, address);
    }


}
