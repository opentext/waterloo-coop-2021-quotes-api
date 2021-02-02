package com.opentext.waterloo.quotesapi.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import com.opentext.waterloo.quotesapi.quote.FetchQuote;
import com.opentext.waterloo.quotesapi.reaction.ReactionController;
import com.opentext.waterloo.quotesapi.reaction.ReactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public Quote getQuoteByDate(@PathVariable("date") String date) {
        return quoteService.getQuoteByDate(date);
    }

    @PostMapping("{uuid}")
    public void incrementLikes(@PathVariable("uuid") UUID uuid, @RequestBody boolean like, HttpServletRequest request) {
        String address = request.getRemoteAddr();
        reactionService.addReaction(uuid, like, address);
    }


}