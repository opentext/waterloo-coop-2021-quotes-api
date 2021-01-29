package com.opentext.waterloo.quotesapi.quote;

import org.json.JSONObject;

public interface FetchQuote {
    JSONObject connect() throws Exception;
}
