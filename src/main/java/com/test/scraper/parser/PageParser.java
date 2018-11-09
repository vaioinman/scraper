package com.test.scraper.parser;

import com.test.scraper.bean.Item;
import org.jsoup.nodes.Document;

import java.util.List;

public interface PageParser {
    List<Item> extractItems(Document pageDocument) throws Exception;
}
