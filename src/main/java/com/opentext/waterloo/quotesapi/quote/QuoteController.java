package com.opentext.waterloo.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "api/v1/quotes")
@Controller
public class QuoteController {
    @Autowired
    private QuoteRepository quoteRepository;
    private final QuoteService quoteService;


    public QuoteController(QuoteRepository quoteRepository, QuoteService quoteService) {
        this.quoteRepository = quoteRepository;
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<Quote> getQuote(){
        return quoteService.findAll();
    }


}
