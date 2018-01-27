package com.tatyanayavkina.youtubechannelstatistic.service.loader;

import com.tatyanayavkina.youtubechannelstatistic.ApplicationSettings;
import com.tatyanayavkina.youtubechannelstatistic.ConnectionSettings;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
public class WebPageDownloaderImpl implements WebPageDownloader {

    private final ConnectionSettings settings;

    @Autowired
    public WebPageDownloaderImpl(ApplicationSettings settings) {
        this.settings = settings.getConnection();
    }

    @Override
    public String getPageContent(String url) throws IOException {
        final URLConnection connection = getConnection(url);
        connection.setRequestProperty("User-Agent", settings.getUserAgent());
        connection.setRequestProperty("Accept-Language", settings.getAcceptLanguage());
        final String encoding = connection.getContentEncoding();
        try (InputStream inputStream = new BufferedInputStream(connection.getInputStream())) {
            return IOUtils.toString(inputStream, encoding);
        }
    }

    private URLConnection getConnection(String pageUrl) throws IOException {
        URL url = new URL(pageUrl);
        URLConnection connection = url.openConnection();
        connection.setReadTimeout(settings.getReadTimeoutMs());
        connection.setConnectTimeout(settings.getConnectTimeoutMs());
        connection.setAllowUserInteraction(false);
        return connection;
    }

}
