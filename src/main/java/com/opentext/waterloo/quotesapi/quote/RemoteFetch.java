package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Component
public class RemoteFetch implements FetchQuote {
    private static final Logger log = LoggerFactory.getLogger(QuotesApiApplication.class);

    @Override
    public Quote connect() throws Exception {
        String builder = null;

        try {
            URL url = new URL("http://quotes.rest/qod.json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(3000);

            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line=bufferedReader.readLine())!=null) {
                stringBuilder.append(line).append('\n');
            }

            bufferedReader.close();
            builder = stringBuilder.toString();
        } catch (java.net.SocketTimeoutException e) {
            log.error("Connection timeout! " + e.getMessage());
        } catch (java.io.IOException e) {
            log.error("IOException occurred! " + e.getMessage());
        }

        JSONObject jsonObject = new JSONObject(builder);
        JSONObject test = new JSONObject(jsonObject.getJSONObject("contents").getJSONArray("quotes").getString(0));

        String quoteOfTheDay=test.get("quote").toString();
        Date date=java.util.Calendar.getInstance().getTime();

        Quote quote = new Quote(UUID.randomUUID(),quoteOfTheDay,date);
        return quote;
    }

//    @Scheduled(cron = "0 0 * * * ?")
//    @CacheEvict(value = "quote", allEntries = true)
    public void clearCache() {
        log.info("Clear cache");
    }
}