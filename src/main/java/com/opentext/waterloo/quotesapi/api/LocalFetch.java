package com.opentext.waterloo.quotesapi.api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
public class LocalFetch implements FetchQuote {
    @Qualifier("localFetch")
    @Override
    public JSONObject connect() throws Exception {
        Resource resource = new ClassPathResource("plain.json");
        File file = resource.getFile();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line=bufferedReader.readLine())!=null) {
            stringBuilder.append(line);
        }

        bufferedReader.close();

        return new JSONObject(stringBuilder.toString());
    }
}
