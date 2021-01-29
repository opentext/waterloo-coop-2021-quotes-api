package com.opentext.waterloo.quotesapi.reaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactionController {

    @RequestMapping
    public String hello() {
        return "hello";
    }
}
