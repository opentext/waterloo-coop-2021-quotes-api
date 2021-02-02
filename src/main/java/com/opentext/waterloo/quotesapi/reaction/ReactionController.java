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
@RequestMapping(path = "api/v1/quotes")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @GetMapping(path = "{date}/reactions")
    public List<Reaction> getReactions(@PathVariable("date") String date) {
        return reactionService.findByQuoteDate(date);
    }

    @PostMapping("{uuid}")
    public void addReaction(@PathVariable("uuid") String id, @JsonProperty @RequestBody boolean like) {
        reactionService.addReaction(id, like);
    }
}
