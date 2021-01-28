package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class QuoteDataAccessService implements QuoteDao{

    private final JdbcTemplate jdbcTemplate;

    public QuoteDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int putQuote(Quote quote) {
       return 0;
    } //this will put the new quote into postgress db


    @Override
    public Optional<Quote> selectQuoteByDate(String date) {
        return Optional.empty();
    }

    @Override
    public int incrementLike(boolean like, Quote quote) {
        quote.addReaction(like);
        return 1;
    }

    @Override
    public List<Quote> allQuotes() {
        final String sql = "SELECT id, text, date FROM quote";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            String quote =resultSet.getString("text");
            String date = resultSet.getString("date");
            return new Quote(id, quote, date);

        });
    }


}
