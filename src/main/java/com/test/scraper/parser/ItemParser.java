package com.test.scraper.parser;

import com.test.scraper.bean.ItemBean;
import com.test.scraper.exception.MalformedDataException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
interface ItemParser {
    ItemBean extractItem(Document html) throws MalformedDataException;

    void extractDescriptionAndNutritionIntoItem(Document givenItemDescription, ItemBean givenItem) throws MalformedDataException;

    ItemBean extractCompleteItem(Document html) throws Exception;
}
