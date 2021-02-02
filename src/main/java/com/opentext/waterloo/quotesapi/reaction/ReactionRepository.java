package com.opentext.waterloo.quotesapi.reaction;

import com.opentext.waterloo.quotesapi.quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReactionRepository extends JpaRepository<Reaction, UUID> {
    List<Reaction> findByQuote(Quote quote);

    List<Reaction> findByAddress(String address);
}
