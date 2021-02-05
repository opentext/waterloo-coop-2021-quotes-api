package com.opentext.waterloo.quotesapi.quote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuoteServiceTest {

    Date test = QuoteService.roundDate(new Date());

    @Autowired
    QuoteRepository quoteRepository;
    @Autowired
    QuoteService quoteService;

    @Test
    void getQuoteByUUID() {
        Quote quote = new Quote("test", test);
        UUID id = quote.getId();
        quoteRepository.save(quote);
        assertEquals(quoteService.getQuoteByUUID(id).getId(), quote.getId());
    }

    @Test
    void getQuoteByDate() {
        Quote quote = new Quote("test", test);
        quoteRepository.save(quote);
        assertEquals(quoteService.getQuoteByDate(test), quote);
    }

    @Test
    void addQuote() {
        Quote quote = new Quote("test", test);
        quoteRepository.save(quote);
        assertThat(quoteRepository).isNotNull();
    }
}
//