package com.opentext.waterloo.quotesapi.api;

import com.opentext.waterloo.quotesapi.model.Quote;
import com.opentext.waterloo.quotesapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/quotes")
@RestController
public class QuotesController {
    private final QuoteService quoteService;

    @Autowired
    public QuotesController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public void addQuote(@RequestBody Quote quote) {
        quoteService.addQuote(quote);
    }

    @GetMapping
    public List<Quote> getAllQuote() {
        return quoteService.getAllQuotes();
    }

    @GetMapping(path = "current?date={date}")
    public Quote getQuoteById(@PathVariable("date") UUID id) {
        return quoteService.getQuoteById(id)
                .orElse(null);
    }
}
