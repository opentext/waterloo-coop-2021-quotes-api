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
        final String sql = "INSERT INTO quote(id, text, date, likes, dislikes) VALUES (uuid_generate_v4(), '" +
                quote.getText() + "', '" + quote.getDate() + "', 0, 0)";
        jdbcTemplate.execute(sql);
        return 1;
    }


    @Override
    public Quote selectQuoteByDate(String date) {
        final String sql = "SELECT id, text, date FROM quote WHERE date='" + date + "'";
        return jdbcTemplate.query(sql, resultSet -> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            String text =resultSet.getString("text");
            String currentDate = resultSet.getString("date");
            return new Quote(id, text, currentDate);
        });
    }

    @Override
    public void incrementLike(boolean like, String date) {
        String sql;
        if (like){
            sql = "UPDATE quote SET likes = likes +1 WHERE date='" + date + "'";
        }
        else{
            sql = "UPDATE quote SET dislikes = dislikes +1 WHERE date= '" +date + "'";
        }
        jdbcTemplate.execute(sql);
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
