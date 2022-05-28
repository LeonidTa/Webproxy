package org.campus02.web;

import org.campus02.exception.CacheMissException;
import org.campus02.exception.UrlLoaderException;

import java.util.HashMap;

public class PageCache {
    private HashMap<String, WebPage> cache = new HashMap<>();

    public HashMap<String, WebPage> getCache() {
        return cache;
    }

    public WebPage readFromCache(String url) throws CacheMissException {

        if (cache.containsKey(url)) {
            return cache.get(url);
        } else {
            throw new CacheMissException("Cache missing!");
        }
    }

    public void writeToCache(WebPage webPage) {
        cache.put(webPage.getUrl(), webPage);
    }

    // What to do here?
    public void warmUp(String pathToUrls) throws UrlLoaderException {
        try {
            cache.put(pathToUrls, UrlLoader.loadWebPage(pathToUrls));
        } catch (UrlLoaderException e) {
            throw new UrlLoaderException("URL couldn't load");
        }
    }

}