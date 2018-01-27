package com.tatyanayavkina.youtubechannelstatistic.service.loader;

import java.io.IOException;

public interface WebPageDownloader {

    String getPageContent(String url) throws IOException;
}
