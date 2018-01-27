package com.tatyanayavkina.youtubechannelstatistic.repository;

import com.tatyanayavkina.youtubechannelstatistic.model.ChannelInformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class ChannelInformationRepositoryImpl implements ChannelInformationRepository {

    private final List<ChannelInformation> channelInformationList;

    public ChannelInformationRepositoryImpl() {
        channelInformationList = Collections.synchronizedList(new LinkedList<>());
    }

    @Override
    public void saveChannelInformation(ChannelInformation channelInformation) {
        channelInformationList.add(channelInformation);
    }

    @Override
    public List<ChannelInformation> getAll() {
        return new ArrayList<>(channelInformationList);
    }
}
