package com.tatyanayavkina.youtubechannelstatistic.service.file;

import com.tatyanayavkina.youtubechannelstatistic.model.ChannelStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVService implements FileService {

    private final static Logger logger = LoggerFactory.getLogger(CSVService.class);

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    @Override
    public void generateFileWithData(List<ChannelStatistic> statistics) {
        String fileName = DATE_FORMAT.format(new Date()) + ".csv";

        List<String> lines = statistics.stream()
                .map(s -> s.getCountry() + "," + s.getChannels() + "," + s.getSubscribers() + "\n")
                .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            for(String line: lines) {
                writer.write(line);
            }
            logger.info("{} was generated, {} channels processed", fileName, statistics.size());
        } catch (IOException e) {
            logger.error("Can not generate file {}", fileName);
            throw new RuntimeException(e);
        }
    }
}
