package com.tatyanayavkina.youtubechannelstatistic.service.loader;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

public interface InformationProcessor {

    void process(Set<String> channelIds);
}
