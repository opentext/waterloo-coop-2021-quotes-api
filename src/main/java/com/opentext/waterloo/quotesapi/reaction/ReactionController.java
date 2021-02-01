package com.opentext.waterloo.quotesapi.reaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.opentext.waterloo.quotesapi.quote.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/v1/reactions")
@Controller
import java.util.List;

@RequestMapping ("/api/v1")
@RestController
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @RequestMapping(value = {"quotes/reactions/hello"})
    public String hello() {
        return "hello";
    }
//
//    @GetMapping(value = {"find"})
//
    @GetMapping("reactions")
    public List<Reaction> getReactions() {
        return reactionService.getAll();
    }

    @PostMapping(value = "quotes/{date}/reactions")
    public void addReaction(@RequestBody boolean isLike) {
        reactionService.addReaction(new Reaction(new Quote(), isLike, "addy"));
    }

}
