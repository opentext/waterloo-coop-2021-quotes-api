package com.opentext.waterloo.quotesapi.Dao;

import com.opentext.waterloo.quotesapi.model.Quote;
import com.opentext.waterloo.quotesapi.model.Reaction;
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
        final String sql = "INSERT INTO quote(id, text, date, likes, dislikes, reactionaddress) VALUES (uuid_generate_v4(), '" +
                quote.getText() + "', '" + quote.getDate() + "', 0, 0, 'null')";
        jdbcTemplate.execute(sql);
        return 1;
    }


    @Override
    public Quote selectQuoteByDate(String date) {
        final String sql = "SELECT id, text, date, likes, dislikes, reactionaddress FROM quote WHERE date='" + date + "'";
        return jdbcTemplate.query(sql, resultSet -> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            String text =resultSet.getString("text");
            String currentDate = resultSet.getString("date");
            int likes = resultSet.getInt("likes");
            int dislikes = resultSet.getInt("dislikes");
            return new Quote(id, text, currentDate, likes, dislikes, null);
        });
    }

    @Override
    public void incrementLike(String like, String date, String address) {
        String sql;
        String bFlag1 = "true";
        if (bFlag1.equalsIgnoreCase(like)){
            sql = "UPDATE quote SET likes = likes +1 WHERE date='" + date + "'";
        }
        else{
            sql = "UPDATE quote SET dislikes = dislikes +1 WHERE date= '" +date + "'";
        }
        //TODO CALL FOR ID AND PUT ADDREACTION AND DON'T RUN IF ADDRESS IS THE SAME???
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<Quote> allQuotes() {
        final String sql = "SELECT id, text, date, likes, dislikes FROM quote";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            String text =resultSet.getString("text");
            String date = resultSet.getString("date");
            int likes = resultSet.getInt("likes");
            int dislikes = resultSet.getInt("dislikes");

            return new Quote(id, text, date, likes, dislikes, null );

        });
    }

    @Override
    public void addReaction(boolean likes, UUID id) {
        final String sql = "INSERT INTO reaction(likes, date, address, Id) VALUES (" + likes + ", ";
        //TODO FIGURE OUT HOW TO ACTUALLY ADD TO REACTION DATABASE AND ADD ADDRESS
    }


}
