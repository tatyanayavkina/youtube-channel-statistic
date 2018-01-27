package com.tatyanayavkina.youtubechannelstatistic.model;

public class ChannelInformation {

    private final String channelId;

    private final String country;

    private final long subscribersCount;

    public ChannelInformation(String channelId, String country, long subscribersCount) {
        this.channelId = channelId;
        this.country = country;
        this.subscribersCount = subscribersCount;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getCountry() {
        return country;
    }

    public long getSubscribersCount() {
        return subscribersCount;
    }

    @Override
    public String toString() {
        return "ChannelInformation={" +
                "channelId=" + channelId +
                ", country=" + country +
                ", subscribersCount=" + subscribersCount
                +"}";
    }
}
