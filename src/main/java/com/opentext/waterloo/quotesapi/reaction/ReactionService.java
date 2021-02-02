package com.opentext.waterloo.quotesapi.reaction;

import com.opentext.waterloo.quotesapi.quote.Quote;
import com.opentext.waterloo.quotesapi.quote.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private QuoteService quoteService;

    public List<Reaction> getAll() {
        return reactionRepository.findAll();
    }

    public List<Reaction> findByQuoteDate(String date) {
        Quote quote = quoteService.getQuoteByDate(date);
        return reactionRepository.findByQuote(quote);
    }

    public void addReaction(UUID uuid, boolean like, String address) {
        Quote quote = quoteService.getQuoteByUUID(uuid);
        reactionRepository.save(new Reaction(quote, like, address));
        quote.incrementLikes(like);
    }
}
