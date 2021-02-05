package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.reaction.Reaction;
import com.opentext.waterloo.quotesapi.reaction.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"*.otxlab.net", "http://localhost:3000"})
@RestController
@RequestMapping(path = "/api/v1/quotes")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @Autowired
    ReactionService reactionService;

    @GetMapping("all")
    public List<Quote> getQuote() {
        return quoteService.getQuotes();
    }

    @PostMapping
    public Quote putQuote(@RequestBody Quote quote) {
        quoteService.addQuote(quote);
        return quote;
    }

    @GetMapping
    public Object getQuoteForToday() {
        return quoteService.getQuoteByDate((new Date()));
    }

    @GetMapping("{date}")
    public Object getQuoteByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return quoteService.getQuoteByDate(date);
    }

    @PostMapping("{uuid}")
    public void incrementLikes(@PathVariable("uuid") UUID uuid, @RequestBody Reaction like, HttpServletRequest request) {
        String address = request.getRemoteAddr();
        reactionService.addReaction(uuid, like.getLike(), address);
    }

    @DeleteMapping
    public void deleteAll(){
        reactionService.deleteAll();
        quoteService.deleteAll();
    }

}
