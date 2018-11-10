package com.test.scraper;

import com.test.scraper.bean.ItemBean;
import com.test.scraper.parser.PageParser;
import com.test.scraper.utility.Fetcher;
import com.test.scraper.utility.ItemsToJsonStringConverter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Scraper {
    @Autowired
    private PageParser pageParser;
    @Autowired
    private Fetcher fetcher;

    public String scrape(String url) throws Exception {
        Document pageDocument = fetcher.fetchDocument(url);
        List<ItemBean> itemBeans = pageParser.extractItems(pageDocument);

        return ItemsToJsonStringConverter.convertToJsonString(itemBeans);
    }
}
