package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuoteService {

    private static final Logger log = LoggerFactory.getLogger(QuotesApiApplication.class);

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

    @Autowired
    private FetchQuote localFetch;
    @Autowired
    private FetchQuote remoteFetch;

    public Quote remoteConnect() throws Exception {
        return remoteFetch.connect();
    }
    public Quote localConnect() throws Exception {
        return localFetch.connect();
    }

    public Quote getQuoteByUUID(UUID uuid) {
        Optional<Quote> quote = quoteRepository.findById(uuid);
        return quote.orElseGet(Quote::new);
    }

    public Quote getQuoteByDate(Date date) {
        Quote quote = quoteRepository.findQuoteByDate(roundDate(date));
        if (quote != null) {
            return quote;
        }
        else {
            return new Quote();
        }
    }

    public void addQuote(Quote quote) {
        quote.roundDate();
        quoteRepository.save(quote);
    }

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void saveQuote() throws Exception {
        Quote quote;
        try {
            log.info("Fetching from theysaidso");
            quote = remoteConnect();
        } catch (Exception e) {
            log.error("Live quote fetch failed! Returning local json file" + e.getMessage());
            quote = localConnect();
        }

        quoteRepository.save(quote);
    }
}
