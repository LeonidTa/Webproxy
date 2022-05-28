package org.campus02.web;

import org.campus02.exception.CacheMissException;
import org.campus02.exception.UrlLoaderException;

import java.io.*;

public class WebProxy {
    private PageCache cache;
    private int numCacheHits;
    private int numCacheMisses;

    public WebProxy() {
        this.cache = new PageCache();
    }

    public WebProxy(PageCache cache) {
        this.cache = cache;
    }

    public WebPage fetch (String url) throws UrlLoaderException {
        WebPage webPage = null;
        try {
            webPage = cache.readFromCache(url);
            numCacheHits++;
        } catch (CacheMissException e) {
            webPage = UrlLoader.loadWebPage(url);
            cache.writeToCache(webPage);
            numCacheMisses++;
        }
        return webPage;
    }

    public String statsHits() {
        return "Stat hits: " + numCacheHits;
    }

    public String statsMisses() {
        return "Stat misses: " + numCacheMisses;
    }

    public boolean writePageCacheToFile(String pathToFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile))){

            for (String key : cache.getCache().keySet()) {
                 bw.write(key + ";" + cache.getCache().get(key).getContent());
                 bw.newLine();
            } bw.flush();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
