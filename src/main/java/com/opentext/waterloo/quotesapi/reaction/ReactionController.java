package com.opentext.waterloo.quotesapi.reaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/api/vi/reaction")
@RestController
public class ReactionController {

    @RequestMapping(value = {"hello"})
    public String hello() {
        return "hello";
    }
//
//    @GetMapping(value = {"find"})
//

}
