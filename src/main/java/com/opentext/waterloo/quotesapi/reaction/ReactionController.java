package com.opentext.waterloo.quotesapi.reaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import com.opentext.waterloo.quotesapi.quote.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "api/v1/reactions")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @GetMapping(path = "{date}/reactions")
    public List<Reaction> getReactions(@PathVariable("date") String date) {
        return reactionService.findByQuoteDate(date);
    }

    @PostMapping(path = "{date}/reactions")
    public void addReaction(@PathVariable("date") String date, @JsonProperty @RequestBody boolean like) {
        reactionService.addReaction(date, like);
    }
}
