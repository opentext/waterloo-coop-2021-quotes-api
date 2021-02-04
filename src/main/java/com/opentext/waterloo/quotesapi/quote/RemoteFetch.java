package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Date;

@Component
public class RemoteFetch implements FetchQuote {
    private static final Logger log = LoggerFactory.getLogger(QuotesApiApplication.class);

    private HttpURLConnection setConnection() throws Exception {
        URL url = new URL("http://quotes.rest/qod.json");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(3000);
        return urlConnection;
    }

    private String readFromAPI(HttpURLConnection urlConnection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line=bufferedReader.readLine())!=null) {
            stringBuilder.append(line).append('\n');
        }

        bufferedReader.close();
        String builder = stringBuilder.toString();

        return builder;
    }

    private JSONObject toJSON(String builder) throws JSONException {
        JSONObject jsonObject = new JSONObject(builder);
        JSONObject test = new JSONObject(jsonObject.getJSONObject("contents")
                .getJSONArray("quotes").getString(0));

        return test;
    }

    @Override
    @Cacheable("quote")
    @Qualifier("remoteFetch")
    public Quote connect() throws Exception {
        HttpURLConnection urlConnection;
        String builder;
        JSONObject test;

        try {
            urlConnection = setConnection();
            builder = readFromAPI(urlConnection);
            test = toJSON(builder);
        } catch(Exception e) {
            throw e;
        }

        String quoteOfTheDay = test.get("quote").toString();

        return new Quote(quoteOfTheDay,new Date(),0,0);
    }
    //divide into 4 methods

    @Scheduled(cron = "0 0 0 * * *")
    @CacheEvict(value = "quote", allEntries = true)
    public void clearCache() {
        log.info("Clear cache");
    }
}
