package com.tatyanayavkina.youtubechannelstatistic;

public class ConnectionSettings {

    private int readTimeoutMs;

    private int connectTimeoutMs;

    private String userAgent;

    private String acceptLanguage;

    public int getReadTimeoutMs() {
        return readTimeoutMs;
    }

    public void setReadTimeoutMs(int readTimeoutMs) {
        this.readTimeoutMs = readTimeoutMs;
    }

    public int getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    public void setConnectTimeoutMs(int connectTimeoutMs) {
        this.connectTimeoutMs = connectTimeoutMs;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    @Override
    public String toString() {
        return "ConnectionSettings{" +
                "readTimeoutMs=" + readTimeoutMs +
                ", connectTimeoutMs=" + connectTimeoutMs +
                ", userAgent='" + userAgent + '\'' +
                ", acceptLanguage='" + acceptLanguage + '\'' +
                '}';
    }
}
