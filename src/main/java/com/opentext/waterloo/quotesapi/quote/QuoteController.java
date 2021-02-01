
package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import com.opentext.waterloo.quotesapi.quote.FetchQuote;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opentext.waterloo.quotesapi.quote.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping(path = "/api/v1/quotes")
@RestController
public class QuoteController {
//    private static final Logger log = LoggerFactory.getLogger(QuotesApiApplication.class);

    private final String DATE_FORMAT = "dd-MM-yyyy";

    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private FetchQuote localFetch;
    @Autowired
    private FetchQuote remoteFetch;

////    @Autowired
////    public QuoteController(QuoteRepository quoteRepository, FetchQuote localFetch, FetchQuote remoteFetch) {
////        this.quoteRepository = quoteRepository;
////        this.localFetch = localFetch;
////        this.remoteFetch = remoteFetch;
////    }
//
    public Quote remoteConnect() throws Exception {
        return remoteFetch.connect();
    }
    public Quote localConnect() throws Exception {
        return localFetch.connect();
    }

    @PostMapping()
    public void addQuote(@RequestBody Quote quote) {
        quoteRepository.addQuote(quote);
    }

    @GetMapping()
    public List<Quote> getAll() {
        return quoteRepository.getAllQuotes();
    }

    @GetMapping("{date}")
    public Quote getQuote(@PathVariable("date") String date) {

        try {
            return quoteRepository.getQuote(new SimpleDateFormat(DATE_FORMAT).parse(date));
        } catch (ParseException e) {
            System.out.println("Date parse error");
            return new Quote();
        }
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

//    @PostMapping(path = "{date}")
//    public void incrementLikes(@RequestBody String like, @PathVariable("date") String date) {
//        quoteRepository.incrementLikes(like, date);
//    }

//    @GetMapping
//    public List<Quote> quotes(){
//        return quoteRepository.quotes();
//    }


//    @PostMapping
//    public void addQuote(@RequestBody Quote quote){
//        quoteRepository.addQuote(quote);
//    }


//    @GetMapping(path = "{date}")
//    public Quote getQuotes(@PathVariable("id") String date) throws Exception {
//        JSONObject json;
//        try {
//            log.info("Fetching from theysaidso");
//            json = remoteConnect();
//        } catch (Exception e) {
//            log.error("Live quote fetch failed! Returning local json file" + e.getMessage());
//            json = localConnect();
//        }
//        JSONObject quote = new JSONObject(json.getJSONObject("contents")
//                .getJSONArray("quotes").getString(0));
//
//        String quoteOfTheDay = quote.get("quote").toString();
//
//        Quote result = new Quote(UUID.randomUUID(), quoteOfTheDay, date, 0, 0, null);
//        return result;
//    }

//    @GetMapping(path = "current?date={date}")
//    public Quote getQuoteByDate(@PathVariable("date") String date) {
//        return quoteRepository.getQuoteByDate(date)
//                .orElse(null);
//    }
}