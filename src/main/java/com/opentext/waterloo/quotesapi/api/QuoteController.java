package com.opentext.waterloo.quotesapi.api;

import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opentext.waterloo.quotesapi.model.Quote;
import com.opentext.waterloo.quotesapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(path = "api/v1/quotes")
@RestController
public class QuoteController {
    private static final Logger log = LoggerFactory.getLogger(QuotesApiApplication.class);
    private final QuoteService quoteService;
    @Autowired
    private final FetchQuote localFetch;
    @Autowired
    private final FetchQuote remoteFetch;

    @Autowired
    public QuoteController(QuoteService quoteService, FetchQuote localFetch, FetchQuote remoteFetch) {
        this.quoteService = quoteService;
        this.localFetch = localFetch;
        this.remoteFetch = remoteFetch;
    }

    public JSONObject remoteConnect() throws Exception {
        return remoteFetch.connect();
    }
    public JSONObject localConnect() throws Exception {
        return localFetch.connect();
    }

    @PostMapping(path = "{date}")
    public void incrementLikes(@RequestBody Boolean like, @PathVariable("date") String date) {
        Quote quote = quoteService.getQuoteByDate(date);
        quoteService.incrementLikes(like, quote);
    }

    @GetMapping(path = "{date}")
    public Quote getQuotes(@PathVariable("id") String date) throws Exception {
        JSONObject json;
        try {
            log.info("Fetching from theysaidso");
            json = remoteConnect();
        } catch (Exception e) { //failed, try to fetch locally stored json
            log.error("Live quote fetch failed! Returning local json file" + e.getMessage());
            json = localConnect();
        }
        JSONObject quote = new JSONObject(json.getJSONObject("contents")
                .getJSONArray("quotes").getString(0));

        String quoteOfTheDay = quote.get("quote").toString();

        Quote result = new Quote(UUID.randomUUID(), quoteOfTheDay, date);
        return result;
    }

//    @GetMapping(path = "current?date={date}")
//    public Quote getQuoteByDate(@PathVariable("date") String date) {
//        return quoteService.getQuoteByDate(date)
//                .orElse(null);
//    }
}
