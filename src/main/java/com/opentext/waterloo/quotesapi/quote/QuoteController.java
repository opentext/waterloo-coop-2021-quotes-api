package com.opentext.waterloo.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "api/v1/quotes")
@Controller
public class QuoteController {
    @Autowired
    private QuoteRepository quoteRepository;




}
