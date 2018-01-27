package com.tatyanayavkina.youtubechannelstatistic.service.parser;

public interface ChannelPageParser {

    CountryAndSubscribers parse(String htmlContent);
}
