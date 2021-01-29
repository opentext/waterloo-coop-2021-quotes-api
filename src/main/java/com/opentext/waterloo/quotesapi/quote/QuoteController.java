package com.opentext.waterloo.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping (path = "api/vi/quotes")
@RestController
public class QuoteController {
    @Autowired
    private QuoteRepository quoteRepository;



}
