package com.tatyanayavkina.youtubechannelstatistic.service.loader;

import java.util.concurrent.CountDownLatch;

public interface ChannelInformationLoader {

    void loadInformation(String channelId, String url, CountDownLatch latch);
}
