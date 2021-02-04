package com.opentext.waterloo.quotesapi.quote;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

@Component
public class LocalFetch implements FetchQuote {
    @Qualifier("localFetch")
    @Override
    public Quote connect() throws Exception {
        Resource resource = new ClassPathResource("plain.json");
        File file = resource.getFile();
        Quote quote;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line=bufferedReader.readLine())!=null) {
            stringBuilder.append(line);
        }

        bufferedReader.close();

        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        JSONObject jsonQuote = new JSONObject(jsonObject.getJSONObject("contents")
                .getJSONArray("quotes").getString(0));

        String quoteOfTheDay = jsonQuote.get("quote").toString();
        Date date=java.util.Calendar.getInstance().getTime();

        quote = new Quote(quoteOfTheDay, date, 0, 0);
        return quote;
    }
}
