package com.test.scraper.parser;

import com.test.scraper.bean.Item;

public interface ItemParser {
    Item extractItem(String html);
}
