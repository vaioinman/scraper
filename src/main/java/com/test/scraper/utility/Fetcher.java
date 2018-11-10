package com.test.scraper.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Fetcher {
    public Document fetchDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
