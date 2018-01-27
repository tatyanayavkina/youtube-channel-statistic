package com.tatyanayavkina.youtubechannelstatistic.service.loader;

import com.tatyanayavkina.youtubechannelstatistic.ApplicationSettings;
import com.tatyanayavkina.youtubechannelstatistic.task.ChannelTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class InformationProcessorImpl implements InformationProcessor {

    private final ExecutorService executorService;

    private final ChannelInformationLoader channelInformationLoader;

    @Autowired
    public InformationProcessorImpl(ChannelInformationLoader channelInformationLoader, ApplicationSettings settings) {
        this.executorService = Executors.newFixedThreadPool(settings.getThreadCount());
        this.channelInformationLoader = channelInformationLoader;
    }

    @Override
    public void process(Set<String> channelIds, CountDownLatch latch) {
        channelIds.forEach((channelId) -> {
            executorService.execute(new ChannelTask(channelId, latch, channelInformationLoader));
        });
    }

    @Override
    public void stop() {
        shutdownAndAwaitTermination(executorService);
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
        }
    }
}