package com.test.scraper.parser;

import com.test.scraper.bean.ItemBean;
import com.test.scraper.utility.Fetcher;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ItemParserImpl implements ItemParser {
    private static final String ITEM_NAME_SELECTOR = "div.product > div.productInfo > div > h3 > a";
    private static final String ITEM_PRICE_SELECTOR = "div.pricing > p.pricePerUnit";
    private static final String ITEM_DESCRIPTION_SELECTOR = "#information > productcontent > htmlcontent > div:nth-child(2)";
    private static final String ITEM_ENERGY_SELECTOR = "#information > productcontent > htmlcontent > div:nth-child(4) > div > div > table > tbody > tr:nth-child(2) > td:nth-child(1)";
    private static final String ITEM_DESCRIPTION_URL_SELECTOR = "div.product > div.productInfo > div > h3 > a";
    private static final String DELIMITER = "/";
    private static final String CURRENCY = "£";
    private static final String ENERGY_UNIT = "kcal";
    private static final String HREF = "abs:href";

    @Autowired
    private Fetcher fetcher;

    @Override
    public ItemBean extractItem(Document html) {
        ItemBean item = null;

        Element nameElement = html.select(ITEM_NAME_SELECTOR).first();
        String productName = nameElement != null ? nameElement.text() : null;

        Element priceElement = html.select(ITEM_PRICE_SELECTOR).first();
        Double productPrice = priceElement != null ? convertToPrice(priceElement.text()) : null;

        if (productName != null && productPrice != null) {
            item = ItemBean.builder()
                    .title(productName)
                    .unitPrice(productPrice)
                    .build();
        }

        return item;
    }

    @Override
    public void extractDescriptionAndNutritionIntoItem(Document html, ItemBean givenItem) {
        Element descriptionElement = html.select(ITEM_DESCRIPTION_SELECTOR).first();
        if (descriptionElement != null) {
            givenItem.setDescription(descriptionElement.text());
        }

        Element energyElement = html.select(ITEM_ENERGY_SELECTOR).first();
        if (energyElement != null) {
            String energyText = energyElement.text();
            try {
                Integer energyValue = Integer.parseInt(
                        energyText.replaceAll(ENERGY_UNIT, "")
                );
                givenItem.setKcalPer100g(energyValue);
            } catch (NumberFormatException ignored) {}
        }
    }

    @Override
    public ItemBean extractCompleteItem(Document html) throws IOException {
        ItemBean item = extractItem(html);

        if (item != null) {
            Element el = html.select(ITEM_DESCRIPTION_URL_SELECTOR).first();
            Document descriptionDocument = fetcher.fetchDocument(el.attr(HREF));
            extractDescriptionAndNutritionIntoItem(descriptionDocument, item);
        }

        return item;
    }

    private Double convertToPrice(String text) {
        String priceString = text.substring(text.indexOf(CURRENCY) + 1, text.indexOf(DELIMITER));

        try {
            return Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
