package com.opentext.waterloo.quotesapi.reaction;

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
    private ReactionRepository reactionRepository;

    @GetMapping
    public List<Reaction> getReactions() {
        return reactionRepository.findAll();
    }


    public void addReaction(Quote quote, boolean likes, UUID quote_uuid) {
        Reaction reaction = new Reaction(quote, likes, "placeholder");
        reactionRepository.save(reaction);
    }

}
