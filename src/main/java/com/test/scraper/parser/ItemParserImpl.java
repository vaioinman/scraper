package com.test.scraper.parser;

import com.test.scraper.bean.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ItemParserImpl implements ItemParser {
    static final String PRODUCT_NAME_QUERY = ".product > .productInfo > .productNameAndPromotions > h3 > a";
    static final String PRODUCT_PRICE_QUERY = ".pricing > .pricePerUnit";
    static final String DELIMITER = "/";
    static final String CURRENCY = "£";

    @Override
    public Item extractItem(String html) {
        Item item = null;

        Document document = Jsoup.parse(html);

        Element nameElement = document.select(PRODUCT_NAME_QUERY).first();
        String productName = nameElement != null ? nameElement.text() : null;

        Element priceElement = document.select(PRODUCT_PRICE_QUERY).first();
        Double productPrice = priceElement != null ? extractPrice(priceElement.text()) : null;

        if (productName != null && productPrice != null) {
            item = Item.builder()
                    .title(productName)
                    .unitPrice(productPrice)
                    .build();
        }

        return item;
    }

    private Double extractPrice(String text) {
        String priceString = text.substring(text.indexOf(CURRENCY) + 1, text.indexOf(DELIMITER));

        try {
            return Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
