package com.opentext.waterloo.quotesapi.reaction;

import com.opentext.waterloo.quotesapi.quote.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    public Quote getQuote(UUID id) {
        return reactionRepository.findByQuoteId(id);
    }

    public void addReaction (Reaction reaction) {
        reactionRepository.save(reaction);
    }

    public List<Reaction> getAll() {
        return new ArrayList<>(reactionRepository.findAll());
    }
}
