package com.test.scraper.parser;

import com.test.scraper.bean.ItemBean;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
interface ItemParser {
    ItemBean extractItem(Document html);

    void extractDescriptionAndNutritionIntoItem(Document givenItemDescription, ItemBean givenItem);

    ItemBean extractCompleteItem(Document html) throws Exception;
}
