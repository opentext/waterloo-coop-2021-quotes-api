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
    } //this will put the new quote into postgress db


    @Override
    public Optional<Quote> selectQuoteByDate(String date) {
        return Optional.empty();
    }

    @Override
    public int incrementLike(boolean like, Quote quote) {


        return 1;
    }


}
