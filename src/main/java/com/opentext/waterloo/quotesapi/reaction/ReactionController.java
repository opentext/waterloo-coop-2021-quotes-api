package com.opentext.waterloo.quotesapi.reaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/quotes")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @GetMapping(path = "{date}/reactions")
    public List<Reaction> getReactions(@PathVariable("date") String date) {
        return reactionService.findByQuoteDate(date);
    }


    @GetMapping
    public List<Reaction> getAll(){
        return reactionService.getAll();
    }

    public void addReaction(String uuid, boolean like, String address){
        reactionService.addReaction(uuid, like, address);
    }


}
