package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.reaction.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/quotes")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @Autowired
    ReactionService reactionService;

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

    @GetMapping
    public List<Quote> getQuote(){
        return quoteService.getQuotes();
    }

    @PostMapping
    public void putQuote(@RequestBody Quote quote){
        quoteService.addQuote(quote);
    }

    @GetMapping("{date}")
    public Quote getQuoteByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return quoteService.getQuoteByDate(date);
    }

    @PostMapping("{uuid}/reactions")
    public void incrementLikes(@PathVariable("uuid") UUID uuid, @RequestBody boolean like, HttpServletRequest request) {
        String address = request.getHeader("X-FORWARDED-FOR");
        reactionService.addReaction(uuid, like, address);
    }


}