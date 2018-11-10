package com.test.scraper.parser;

import com.test.scraper.bean.ItemBean;
import com.test.scraper.exception.MalformedDataException;
import com.test.scraper.utility.Fetcher;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

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
    public ItemBean extractItem(Document html) throws MalformedDataException {
        Element nameElement = html.select(ITEM_NAME_SELECTOR).first();
        if (nameElement == null || nameElement.text().isEmpty()) {
            throw new MalformedDataException("Name/title section cannot be found or empty.");
        }
        String itemName = nameElement.text();

        Element priceElement = html.select(ITEM_PRICE_SELECTOR).first();
        if (priceElement == null) {
            throw new MalformedDataException("Price per unit section cannot be found.");
        }
        Double productPrice = convertToPrice(priceElement.text());

        return ItemBean.builder()
                .title(itemName)
                .unitPrice(productPrice)
                .build();
    }

    @Override
    public void extractDescriptionAndNutritionIntoItem(Document html, ItemBean givenItem)
            throws MalformedDataException {

        Element descriptionElement = html.select(ITEM_DESCRIPTION_SELECTOR).first();
        if (descriptionElement == null) {
            throw new MalformedDataException("Description section cannot be found.");
        }
        String description = descriptionElement.text().indexOf("\n") != -1
                ? descriptionElement.text().substring(descriptionElement.text().indexOf("\n"))
                : descriptionElement.text();
        givenItem.setDescription(description);

        Element energyElement = html.select(ITEM_ENERGY_SELECTOR).first();
        if (energyElement == null) {
            throw new MalformedDataException("Energy (kcal per 100g) section cannot be found.");
        }
        givenItem.setKcalPer100g(convertToEnergy(energyElement.text()));
    }

    @Override
    public ItemBean extractCompleteItem(Document html) throws IOException, MalformedDataException {
        ItemBean item = extractItem(html);

        Element el = html.select(ITEM_DESCRIPTION_URL_SELECTOR).first();
        if (el == null) {
            throw new MalformedDataException("Link to description is not found.");
        }

        Document descriptionDocument = fetcher.fetchDocument(el.attr(HREF));
        extractDescriptionAndNutritionIntoItem(descriptionDocument, item);

        return item;
    }

    private Double convertToPrice(String text) throws MalformedDataException {
        String priceString = text.substring(text.indexOf(CURRENCY) + 1, text.indexOf(DELIMITER));

        try {
            return Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            throw new MalformedDataException("Cannot convert price per unit text.", e);
        }
    }

    private Integer convertToEnergy(String text) throws MalformedDataException {
        try {
            return Integer.parseInt(
                    text.replaceAll(ENERGY_UNIT, "")
            );
        } catch (NumberFormatException e) {
            throw new MalformedDataException("Cannot convert energy text.", e);
        }
    }
}
