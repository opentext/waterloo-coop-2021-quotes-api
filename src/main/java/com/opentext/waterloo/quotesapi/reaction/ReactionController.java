package com.opentext.waterloo.quotesapi.reaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;
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

}
