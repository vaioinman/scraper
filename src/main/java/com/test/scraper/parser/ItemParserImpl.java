package com.test.scraper.parser;

import com.test.scraper.bean.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ItemParserImpl implements ItemParser {
    final String PRODUCT_NAME_QUERY = ".product > .productInfo > .productNameAndPromotions > h3 > a";

    @Override
    public Item extractItem(String html) {
        Item item = null;

        Document document = Jsoup.parse(html);
        Elements elements = document.select(PRODUCT_NAME_QUERY);
        if (!elements.isEmpty()) {
            item = Item.builder()
                    .title(elements.first().text())
                    .build();
        }

        return item;
    }
}
