package com.opentext.waterloo.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/quotes")
public class QuoteController {


    @Autowired
    private QuoteRepository quoteRepository;


    @GetMapping
    public List<Quote> getQuote(){
        return quoteRepository.findAll();
    }

    @PostMapping
    public void putQuote(@RequestBody Quote quote){
        quoteRepository.save(quote);
    }

    @PostMapping(path = "{uuid}/{likes}")
    public void incrementLikes(@PathVariable("uuid") String uuid, @PathVariable ("likes") String like) {
        quoteRepository.incrementLikes(uuid, like);
    }



}