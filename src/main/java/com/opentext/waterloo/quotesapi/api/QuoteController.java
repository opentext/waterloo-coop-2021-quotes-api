package com.opentext.waterloo.quotesapi.api;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class QuoteController {
    private final QuoteController quoteController;

    public QuoteController(QuoteService quoteService){
        this.quoteService = quoteService;
    }

    public void addQuote(@Valid @NonNull @RequestBody Quote quote){
        quoteService.addQuote(quote);
    }

    @GetMapping (path = "")


}
