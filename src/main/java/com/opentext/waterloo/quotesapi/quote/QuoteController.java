package com.opentext.waterloo.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "api/v1/quotes")
@Controller
public class QuoteController {
    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping
    public List<Quote> getQuote(){
        List<Quote> myList = quoteRepository.findAll();
        for(int i = 0; i< myList.size(); i++){
            System.out.println (myList.get(i).getText());
        }
        return quoteRepository.findAll();
    }

    @PostMapping
    public void putQuote(@RequestBody Quote quote){
        quoteRepository.save(quote);
    }


}