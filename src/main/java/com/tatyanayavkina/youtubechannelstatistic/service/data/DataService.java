package com.tatyanayavkina.youtubechannelstatistic.service.data;

import com.tatyanayavkina.youtubechannelstatistic.model.ChannelStatistic;

import java.util.List;

public interface DataService {

    List<ChannelStatistic> getChannelStatistics();
}
