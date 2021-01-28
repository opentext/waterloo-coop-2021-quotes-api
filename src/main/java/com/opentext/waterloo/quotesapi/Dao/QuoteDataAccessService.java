package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("postgres")
public class QuoteDataAccessService implements QuoteDao{

    private final JdbcTemplate jdbcTemplate;

    public QuoteDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int putQuote(Quote quote) {
        final String sql = "INSERT INTO quote(id, text, date) VALUES (uuid_generate_v4(), " +
                quote.getText() + "', '" + quote.getDate() + "'";
        jdbcTemplate.query(sql, resultSet -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String text = resultSet.getString("text");
            String date = resultSet.getString("date");
            return new Quote (id, text, date);
        });
        return 1;
    }


    @Override
    public Quote selectQuoteByDate(String date) {
        final String sql = "SELECT id, text, date FROM quote WHERE date =" + date;
        return jdbcTemplate.query(sql, resultSet -> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            String text =resultSet.getString("text");
            String currentDate = resultSet.getString("date");
            return new Quote(id, text, currentDate);
        });
    }

    @Override
    public int incrementLike(boolean like, String date) {
        if (like){
            String sql = "UPDATE quote SET likes = likes +1";
        }
        else{
            String sql = "UPDATE quote SET dislikes = dislikes +1";
        }
        jdbcTemplate.query(sql, resultSet -> {

        });
        return 1;
    }

    @Override
    public List<Quote> allQuotes() {
        final String sql = "SELECT id, text, date FROM quote";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            String text =resultSet.getString("text");
            String date = resultSet.getString("date");
            return new Quote(id, text, date);

        });
    }


}
