package com.tatyanayavkina.youtubechannelstatistic.service.file;

import com.tatyanayavkina.youtubechannelstatistic.model.ChannelStatistic;

import java.util.List;

public interface FileService {

    void generateFileWithData(List<ChannelStatistic> statistics);
}
