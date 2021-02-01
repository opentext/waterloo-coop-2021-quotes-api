package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import com.opentext.waterloo.quotesapi.quote.FetchQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/quotes")
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;

/*    @Autowired
    private FetchQuote localFetch;
    @Autowired
    private FetchQuote remoteFetch;

    public JSONObject remoteConnect() throws Exception {
        return remoteFetch.connect();
    }
    public JSONObject localConnect() throws Exception {
        return localFetch.connect();
    }
*/
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

//    @GetMapping("{date}")
//    public Quote getQuote(@PathVariable("date") String date) {
//
//        try {
//            return quoteRepository.findQuoteByDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
//        } catch (ParseException e) {
//            System.out.println("Date parse error");
//            return new Quote();
//        }
//    }

}