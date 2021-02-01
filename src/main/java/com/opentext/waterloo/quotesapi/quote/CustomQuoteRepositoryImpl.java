package com.opentext.waterloo.quotesapi.quote;



import com.opentext.waterloo.quotesapi.reaction.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomQuoteRepositoryImpl implements CustomQuoteRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomQuoteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int incrementLikes(String uuid, String like) {
        //TODO JUST HARD CODE IT AGAIN
        String sql;
        JdbcTemplate jdbcTemplate1 = this.jdbcTemplate;
        boolean check = like.equalsIgnoreCase("true");
        if (check){
            sql = "UPDATE quote SET likes = likes +1 WHERE quote_uuid='" + uuid + "'";
        }
        else{
            sql = "UPDATE quote SET dislikes = dislikes +1 WHERE quote_uuid= '" +uuid + "'";
        }
        jdbcTemplate.execute(sql);
        //To make the quote object, make a jdbcTemplate.query, then make a new quote, submit it with blank address(temp) and boolean check
        Reaction reaction = new Reaction(jdbcTemplate1.query(sql, resultSet -> {
            UUID quote_uuid =UUID.fromString(resultSet.getString("quote_uuid"));
            String text =resultSet.getString("text");
            Date currentDate = resultSet.getDate("date");
            return new Quote(text, currentDate, quote_uuid);
        }), check, "" );
        jdbcTemplate.execute("INSERT INTO reaction(id, address, date, is_like, quote_quote_uuid) VALUES (" + reaction.getId() +", '" +reaction.getAddress() +
                "', " +reaction.getLike() + ", '" + reaction.getQuoteId() + "'" );

        return 1;
    }
}
