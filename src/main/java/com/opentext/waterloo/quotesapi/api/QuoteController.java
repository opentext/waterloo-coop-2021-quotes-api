package com.opentext.waterloo.quotesapi.api;

import com.opentext.waterloo.quotesapi.model.Quote;
import com.opentext.waterloo.quotesapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(path = "api/v1/quotes")
@RestController
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public void addQuote(@RequestBody Quote quote) {
        quoteService.addQuote(quote);
    }

    @GetMapping
    public List<Quote> quotes() {
        return quoteService.quotes();
    }

//    @GetMapping(path = "current?date={date}")
//    public Quote getQuoteByDate(@PathVariable("date") String date) {
//        return quoteService.getQuoteByDate(date)
//                .orElse(null);
//    }
}
