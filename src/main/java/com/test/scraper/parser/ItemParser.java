package com.test.scraper.parser;

import com.test.scraper.bean.Item;
import org.jsoup.nodes.Document;

import java.io.IOException;

public interface ItemParser {
    Item extractItem(Document html);

    void extractDescriptionAndNutritionIntoItem(Document givenItemDescription, Item givenItem);

    Item extractCompleteItem(Document html) throws IOException;
}
