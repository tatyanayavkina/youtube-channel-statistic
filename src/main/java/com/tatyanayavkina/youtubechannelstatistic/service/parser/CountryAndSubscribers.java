package com.tatyanayavkina.youtubechannelstatistic.service.parser;

public class CountryAndSubscribers {

    private final String country;

    private final long subscribers;

    public CountryAndSubscribers(String country, long subscribers) {
        this.country = country;
        this.subscribers = subscribers;
    }

    public String getCountry() {
        return country;
    }

    public long getSubscribers() {
        return subscribers;
    }
}
