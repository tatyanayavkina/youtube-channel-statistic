package com.tatyanayavkina.youtubechannelstatistic.service.loader;

import com.tatyanayavkina.youtubechannelstatistic.ApplicationSettings;
import com.tatyanayavkina.youtubechannelstatistic.task.ChannelTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.*;

@Service
public class InformationProcessorImpl implements InformationProcessor {

    private final static Logger logger = LoggerFactory.getLogger(InformationProcessorImpl.class);

    private final ExecutorService executorService;

    private final ChannelInformationLoader channelInformationLoader;

    @Autowired
    public InformationProcessorImpl(ChannelInformationLoader channelInformationLoader, ApplicationSettings settings) {
        this.executorService = Executors.newFixedThreadPool(settings.getThreadCount());
        this.channelInformationLoader = channelInformationLoader;
    }

    @Override
    public void process(Set<String> channelIds) {
        CompletableFuture<?>[] tasks = channelIds.stream()
                .map(channelId -> CompletableFuture.runAsync(new ChannelTask(channelId, channelInformationLoader), executorService))
                .map(task -> task.handle((result, exception) -> {
                    if (exception != null) {
                        logger.warn("Channel task was completed wih error={}", exception);
                    }
                    return result;
                }))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
        shutdownAndAwaitTermination(executorService);
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    logger.error("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
        }
    }
}