package com.opentext.waterloo.quotesapi.reaction;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import com.opentext.waterloo.quotesapi.quote.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/reactions")
public class ReactionController {

    @Autowired
    private ReactionRepository reactionRepository;


    @GetMapping
    public List<Reaction> getReactions() {
        return reactionRepository.findAll();
    }

    @PostMapping(value = "quotes/{date}/reactions")
    public void addReaction(@RequestBody boolean isLike) {
        System.out.println("IT WORKS");
    }

}
