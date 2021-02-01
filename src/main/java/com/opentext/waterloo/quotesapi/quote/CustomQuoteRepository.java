package com.opentext.waterloo.quotesapi.quote;

public interface CustomQuoteRepository {
    int incrementLikes(String uuid, String like);
}
