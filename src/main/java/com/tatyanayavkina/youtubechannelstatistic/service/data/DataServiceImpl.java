package com.tatyanayavkina.youtubechannelstatistic.service.data;

import com.tatyanayavkina.youtubechannelstatistic.model.ChannelInformation;
import com.tatyanayavkina.youtubechannelstatistic.model.ChannelStatistic;
import com.tatyanayavkina.youtubechannelstatistic.repository.ChannelInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {

    private final ChannelInformationRepository channelInformationRepository;

    @Autowired
    public DataServiceImpl(ChannelInformationRepository channelInformationRepository) {
        this.channelInformationRepository = channelInformationRepository;
    }

    @Override
    public List<ChannelStatistic> getChannelStatistics() {
        return channelInformationRepository.getAll().stream()
                .collect(Collectors.groupingBy(ChannelInformation::getCountry))
                .entrySet()
                .stream()
                .map(e -> {
                    String country = e.getKey();
                    List<ChannelInformation> channels = e.getValue();
                    return new ChannelStatistic(country, channels.size(), calculateSubscribersCountForGroup(channels));
                })
                .sorted((a, b) -> Long.compare(b.getSubscribers(), a.getSubscribers()))
                .collect(Collectors.toList());
    }

    private long calculateSubscribersCountForGroup(List<ChannelInformation> channels) {
        return channels.stream()
                .map(ChannelInformation::getSubscribersCount)
                .reduce(0L, Long::sum);
    }
}
