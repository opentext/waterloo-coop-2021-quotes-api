package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.reaction.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    public Quote getQuoteByUUID(String uuid_string) {
        UUID uuid = UUID.fromString(uuid_string);
        Optional<Quote> quote = quoteRepository.findById(uuid);
        return quote.orElseGet(Quote::new);
    }

    public Quote getQuoteByDate(String date) {
        Quote quote;
        try {
            quote = quoteRepository.findQuoteByDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            System.out.println("Date parse error");
            return new Quote();
        }

        if (quote == null) {
            System.out.println("Quote not found");

        }
        return new Quote();
    }

    public void addQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public List<Quote> getQuotes() {
        return  quoteRepository.findAll();
    }
}
