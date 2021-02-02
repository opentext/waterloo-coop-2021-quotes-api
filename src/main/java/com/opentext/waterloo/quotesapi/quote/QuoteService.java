package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import org.json.JSONObject;
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

    @Scheduled(cron = "* */5 * * * *")
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
