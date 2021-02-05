package com.opentext.waterloo.quotesapi.reaction;

import com.opentext.waterloo.quotesapi.quote.Quote;
import com.opentext.waterloo.quotesapi.quote.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Transactional
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void addReaction(UUID uuid, boolean like, String address) {
        // isolates address, then quote id
        List<Reaction> reaction = reactionRepository.findByAddress(address);
        if (!reaction.isEmpty()) {
            for (Reaction r : reaction) {
                if (r.getQuoteId().equals(uuid)) {
                    return;
                }
            }
        }

        Quote quote = quoteService.getQuoteByUUID(uuid);
        reactionRepository.save(new Reaction(quote, like, address));
        quote.incrementLikes(like);
        quoteService.addQuote(quote);
    }

    public void deleteAll(){
        reactionRepository.deleteAll();
    }

}
