package com.test.scraper.parser;

import com.test.scraper.bean.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class ItemParserImpl implements ItemParser {
    private static final String PRODUCT_NAME_SELECTOR = "div.product > div.productInfo > div > h3 > a";
    private static final String PRODUCT_PRICE_SELECTOR = "div.pricing > p.pricePerUnit";
    private static final String PRODUCT_DESCRIPTION_SELECTOR = "#information > productcontent > htmlcontent > div:nth-child(2)";
    private static final String PRODUCT_ENERGY_SELECTOR = "#information > productcontent > htmlcontent > div:nth-child(4) > div > div > table > tbody > tr:nth-child(2) > td:nth-child(1)";
    private static final String DELIMITER = "/";
    private static final String CURRENCY = "£";
    private static final String ENERGY_UNIT = "kcal";

    @Override
    public Item extractItem(String html) {
        Item item = null;

        Document document = Jsoup.parse(html);

        Element nameElement = document.select(PRODUCT_NAME_SELECTOR).first();
        String productName = nameElement != null ? nameElement.text() : null;

        Element priceElement = document.select(PRODUCT_PRICE_SELECTOR).first();
        Double productPrice = priceElement != null ? extractPrice(priceElement.text()) : null;

        if (productName != null && productPrice != null) {
            item = Item.builder()
                    .title(productName)
                    .unitPrice(productPrice)
                    .build();
        }

        return item;
    }

    @Override
    public void extractDescriptionAndNutrition(String givenItemDescription, Item givenItem) {
        Document document = Jsoup.parse(givenItemDescription);

        Element descriptionElement = document.select(PRODUCT_DESCRIPTION_SELECTOR).first();
        if (descriptionElement != null) {
            givenItem.setDescription(descriptionElement.text());
        }

        Element energyElement = document.select(PRODUCT_ENERGY_SELECTOR).first();
        if (energyElement != null) {
            String energyText = energyElement.text();
            try {
                Integer energyValue = Integer.parseInt(energyText.replaceAll(ENERGY_UNIT, ""));
                givenItem.setKcalPer100g(energyValue);
            } catch (NumberFormatException ignored) {}
        }
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
