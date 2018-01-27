package com.tatyanayavkina.youtubechannelstatistic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "settings")
public class ApplicationSettings {

    private String filePath;

    private ConnectionSettings connection;

    private int threadCount;

    private String noCountry;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ConnectionSettings getConnection() {
        return connection;
    }

    public void setConnection(ConnectionSettings connection) {
        this.connection = connection;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public String getNoCountry() {
        return noCountry;
    }

    public void setNoCountry(String noCountry) {
        this.noCountry = noCountry;
    }

    @Override
    public String toString() {
        return "ApplicationSettings{" +
                "filePath='" + filePath + '\'' +
                ", connection=" + connection +
                ", threadCount=" + threadCount +
                ", noCountry='" + noCountry + '\'' +
                '}';
    }
}
