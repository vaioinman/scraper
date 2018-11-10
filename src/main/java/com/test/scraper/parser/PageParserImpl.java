package com.test.scraper.parser;

import com.test.scraper.bean.ItemBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageParserImpl implements PageParser {

    private static final String GRID_SELECTOR = ".productLister > .gridItem";

    @Autowired
    private ItemParser itemParser;

    @Override
    public List<ItemBean> extractItems(Document pageDocument) {
        Elements elements = pageDocument.select(GRID_SELECTOR);
        List<ItemBean> items = new ArrayList<>();

        for (Element element: elements) {
            try {
                Document itemDocument = Jsoup.parse(element.html());
                items.add(itemParser.extractCompleteItem(itemDocument));
            } catch (Exception e) {
            }
        }

        return items;
    }
}
