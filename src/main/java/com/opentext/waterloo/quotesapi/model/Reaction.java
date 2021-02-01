package com.opentext.waterloo.quotesapi.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Reaction {

    private final boolean like;
    private final String date;
    private final String address;

    public Reaction(boolean like, String date, String address) {
        this.like = like;
        this.date = date;
        this.address = address;
    }

    public Reaction(boolean like) {
        this.like = like;
        this.date = getCurrentISODate();
        this.address = "";
    }

    private String getCurrentISODate() {
        TimeZone tz = TimeZone.getTimeZone("EST");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(tz);
        return df.format(new Date());
    }

    public boolean getLike() {
        return like;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

}
