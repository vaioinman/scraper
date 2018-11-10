package com.test.scraper.parser;

import com.test.scraper.bean.ItemBean;
import com.test.scraper.validator.Validator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Configurable
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
                Document itemDocument = Jsoup.parse(element.html(), element.baseUri());
                ItemBean item = itemParser.extractCompleteItem(itemDocument);
                if (Validator.validate(item)) {
                    items.add(item);

                }
            } catch (Exception e) {
                System.err.println("Error found but carry on. Printing stacktrace...");
                e.printStackTrace();
            }
        }

        return items;
    }
}
