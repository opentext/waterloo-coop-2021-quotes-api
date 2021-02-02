package com.opentext.waterloo.quotesapi.reaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/quotes")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @GetMapping(path = "{date}/reactions")
    public List<Reaction> getReactions(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return reactionService.findByQuoteDate(date);
    }

    @GetMapping("reactions")
    public List<Reaction> getAll(){
        return reactionService.getAll();
    }

}
