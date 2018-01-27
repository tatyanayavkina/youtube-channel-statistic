package com.tatyanayavkina.youtubechannelstatistic.model;

public class ChannelStatistic {

    private final String country;

    private final int channels;

    private final long subscribers;

    public ChannelStatistic(String country, int channels, long subscribers) {
        this.country = country;
        this.channels = channels;
        this.subscribers = subscribers;
    }

    public String getCountry() {
        return country;
    }

    public int getChannels() {
        return channels;
    }

    public long getSubscribers() {
        return subscribers;
    }

    @Override
    public String toString() {
        return "ChannelStatistic{" +
                "country='" + country + '\'' +
                ", channels=" + channels +
                ", subscribers=" + subscribers +
                '}';
    }
}
