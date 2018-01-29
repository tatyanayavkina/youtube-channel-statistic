package com.tatyanayavkina.youtubechannelstatistic;

import com.tatyanayavkina.youtubechannelstatistic.model.ChannelStatistic;
import com.tatyanayavkina.youtubechannelstatistic.service.data.DataService;
import com.tatyanayavkina.youtubechannelstatistic.service.file.FileService;
import com.tatyanayavkina.youtubechannelstatistic.service.loader.InformationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class YoutubeChannelStatisticApplication implements CommandLineRunner {

    @Autowired
    private ApplicationSettings settings;
    @Autowired
    private InformationProcessor informationProcessor;
    @Autowired
    private DataService dataService;
    @Autowired
    private FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(YoutubeChannelStatisticApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Set<String> channelIds = readIdsFromFile(settings.getFilePath());
        informationProcessor.process(channelIds);
        List<ChannelStatistic> statistics = dataService.getChannelStatistics();
        fileService.generateFileWithData(statistics);
    }

    private Set<String> readIdsFromFile(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Can not process file " + fileName);
        }
    }
}
