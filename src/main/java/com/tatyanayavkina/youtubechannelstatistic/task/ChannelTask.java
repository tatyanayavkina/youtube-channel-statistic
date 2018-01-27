package com.tatyanayavkina.youtubechannelstatistic.task;

import com.tatyanayavkina.youtubechannelstatistic.service.loader.ChannelInformationLoader;

import java.util.concurrent.CountDownLatch;

public class ChannelTask implements Runnable {

    private final static String YOUTUBE_URL = "https://www.youtube.com/channel/{CHANNEL_ID}/about";

    private final String channelId;

    private final String url;

    private final CountDownLatch latch;

    private final ChannelInformationLoader channelInformationLoader;

    public ChannelTask(String channelId, CountDownLatch latch, ChannelInformationLoader channelInformationLoader) {
        this.channelId = channelId;
        this.url = YOUTUBE_URL.replace("{CHANNEL_ID}", channelId);
        this.latch = latch;
        this.channelInformationLoader = channelInformationLoader;
    }

    @Override
    public void run() {
        channelInformationLoader.loadInformation(channelId, url, latch);
    }
}
