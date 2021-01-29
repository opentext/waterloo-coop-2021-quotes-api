package com.opentext.waterloo.quotesapi.quote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuoteRepository extends JpaRepository<Quote, UUID> {
}