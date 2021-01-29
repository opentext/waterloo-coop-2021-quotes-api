package com.opentext.waterloo.quotesapi.quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

public interface QuoteRepository extends JpaRepository<Quote, UUID> {

    public Quote findQuoteByDate(Date date);
}
