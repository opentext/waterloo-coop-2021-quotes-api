package com.opentext.waterloo.quotesapi.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import com.opentext.waterloo.quotesapi.quote.FetchQuote;
import com.opentext.waterloo.quotesapi.reaction.ReactionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/quotes")
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    ReactionController reactionController;

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

    @PostMapping(path = "{uuid}/reactions")
    public void incrementLikes(@PathVariable("uuid") String uuid, @JsonProperty @RequestBody boolean like) {
        Optional<Quote> quoteLiked = quoteRepository.findById(UUID.fromString(uuid));
        if(quoteLiked.isPresent()) {
            quoteLiked.get().incrementLikes(like);
            quoteRepository.save(quoteLiked.get());
            reactionController.addReaction(quoteLiked.get(), like, UUID.fromString(uuid));

        }
    }

    @GetMapping("{date}")
    public Quote getQuote(@PathVariable("date") String date) {

        try {
            Date df = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//            Date d = new SimpleDateFormat("").parse("2020-01-30T00:00:00.000+00:00")
            System.out.println(df);
            return quoteRepository.findQuoteByDate(df);
        } catch (ParseException e) {
            System.out.println("Date parse error");
            return new Quote();
        }
    }

}