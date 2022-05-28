package org.campus02.web;

import org.campus02.exception.UrlLoaderException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlLoader {

    public static WebPage loadWebPage(String url) throws UrlLoaderException {
        try {
            URL myUrl = new URL(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(myUrl.openStream()));

            String totalContent = null;
            String line;
            while ((line = br.readLine()) != null) {
                totalContent = totalContent + line;
            }
            System.out.println(totalContent);

            return new WebPage(url, totalContent);

        } catch (Exception e) {
            throw new UrlLoaderException("cannot load URL ", e);
        }
    }
}
