package com.opentext.waterloo.quotesapi.quote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuoteTest {

    @Autowired private QuoteRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testText() {
        Quote quote = new Quote();
        quote.setText("Some quote of the day");
        assertEquals("Some quote of the day", quote.getText());
    }

    @Test
    public void createQuote() {
        Quote quote = new Quote();
        quote.setText("Some quote of the day");

        long count = repository.count();

        repository.save(quote);

        assertEquals(count + 1, repository.count());
    }
}
