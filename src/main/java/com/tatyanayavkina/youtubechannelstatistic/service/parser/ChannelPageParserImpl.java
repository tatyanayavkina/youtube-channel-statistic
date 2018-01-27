package com.tatyanayavkina.youtubechannelstatistic.service.parser;

import com.tatyanayavkina.youtubechannelstatistic.ApplicationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChannelPageParserImpl implements ChannelPageParser {

    // "country":{"simpleText":"Мексика"}
    private final Pattern COUNTRY_PATTERN = Pattern.compile("\"country\":\\{\"simpleText\":\"([^\"]+?)\"\\}");
   // "subscriberCountText":{"runs":[{"text":"10 578 подписчиков"}]}
    private final Pattern SUBSCRIBERS_PATTERN = Pattern.compile("\"subscriberCountText\":\\{\"runs\":\\[\\{\"text\":\"([^\"]+?)\"\\}\\]\\}");

    private final String defaultCountry;

    @Autowired
    public ChannelPageParserImpl(ApplicationSettings settings) {
        this.defaultCountry = settings.getNoCountry();
    }

    @Override
    public CountryAndSubscribers parse(String pageContent) {
        String country = getCountry(pageContent);
        long subscribers = getSubscribers(pageContent);

        return new CountryAndSubscribers(country, subscribers);
    }

    private long getSubscribers(String pageContent) {

        Matcher matcher = SUBSCRIBERS_PATTERN.matcher(pageContent);
        if (!matcher.find()) {
            throw new RuntimeException("Can not find subscribers info");
        }

        StringBuilder subscribersCountBuilder = new StringBuilder();
        char[] subscribersText = matcher.group().toCharArray();
        for (char letter: subscribersText) {
            if (Character.isDigit(letter)) {
                subscribersCountBuilder.append(letter);
            }
        }

        return Long.valueOf(subscribersCountBuilder.toString());
    }

    private String getCountry(String pageContent) {
        Matcher matcher = COUNTRY_PATTERN.matcher(pageContent);
        if (!matcher.find()) {
            return defaultCountry;
        }

        return matcher.group(1);
    }
}
