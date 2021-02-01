package com.opentext.waterloo.quotesapi.quote;

import org.hibernate.CustomEntityDirtinessStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;

public interface QuoteRepository extends JpaRepository<Quote, UUID>, CustomQuoteRepository {

//    public Quote findQuoteByDate(Date date);
}
