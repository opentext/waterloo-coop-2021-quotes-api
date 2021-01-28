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
        //TODO CODE TO PUT STUFF INTO THE POSTGRESDB

        return 0;
    }


    @Override
    public Optional<Quote> selectQuoteByDate(String date) {
        final String sql = "SELECT id, text, date FROM date";
        jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            String quote =resultSet.getString("text");
            String currentDate = resultSet.getString("date");
            if (date == currentDate) {
                return new Quote(id, quote, currentDate);
            }
            else{
                //FIXME THIS IS WRONG AND NEEDS TO BE REDONE
                return new Quote(id, quote, date);
            }
        });
        return null; //FIXME THIS SHOULDN'T BE NULL
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
