package com.opentext.waterloo.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    public static Date roundDate(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public Quote getQuoteByUUID(UUID uuid) {
        Optional<Quote> quote = quoteRepository.findById(uuid);
        return quote.orElseGet(Quote::new);
    }

    public Quote getQuoteByDate(Date date) {
        return Objects.requireNonNullElseGet(
                quoteRepository.findQuoteByDate(roundDate(date)),
                Quote::new);
    }

    public void addQuote(Quote quote) {
        quote.roundDate();
        quoteRepository.save(quote);
    }

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }
}
