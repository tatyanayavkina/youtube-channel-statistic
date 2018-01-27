package com.tatyanayavkina.youtubechannelstatistic.repository;

import com.tatyanayavkina.youtubechannelstatistic.model.ChannelInformation;

import java.util.List;

public interface ChannelInformationRepository {

    void saveChannelInformation(ChannelInformation channelInformation);

    List<ChannelInformation> getAll();
}
