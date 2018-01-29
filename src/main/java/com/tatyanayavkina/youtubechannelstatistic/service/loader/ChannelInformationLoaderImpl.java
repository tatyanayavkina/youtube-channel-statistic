package com.tatyanayavkina.youtubechannelstatistic.service.loader;

import com.tatyanayavkina.youtubechannelstatistic.model.ChannelInformation;
import com.tatyanayavkina.youtubechannelstatistic.service.parser.CountryAndSubscribers;
import com.tatyanayavkina.youtubechannelstatistic.service.parser.ChannelPageParser;
import com.tatyanayavkina.youtubechannelstatistic.repository.ChannelInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class ChannelInformationLoaderImpl implements ChannelInformationLoader {

    private final static Logger logger = LoggerFactory.getLogger(ChannelInformationLoaderImpl.class);

    private final ChannelInformationRepository repository;

    private final WebPageDownloader webPageDownloader;

    private final ChannelPageParser channelPageParser;

    @Autowired
    public ChannelInformationLoaderImpl(ChannelInformationRepository repository,
                                        WebPageDownloader webPageDownloader,
                                        ChannelPageParser channelPageParser) {
        this.repository = repository;
        this.webPageDownloader = webPageDownloader;
        this.channelPageParser = channelPageParser;
    }

    @Override
    public void loadInformation(String channelId, String url) {
        try {
            String html = webPageDownloader.getPageContent(url);
            CountryAndSubscribers countryAndSubscribers = channelPageParser.parse(html);
            ChannelInformation channelInformation = new ChannelInformation(channelId,
                    countryAndSubscribers.getCountry(),
                    countryAndSubscribers.getSubscribers());

            repository.saveChannelInformation(channelInformation);
        } catch (Exception e) {
            logger.error("Can not get page content for channelId={}", channelId);
        }
    }
}
