package com.test.scraper.parser;

import com.test.scraper.bean.ItemBean;
import org.jsoup.nodes.Document;

import java.util.List;

interface PageParser {
    List<ItemBean> extractItems(Document pageDocument) throws Exception;
}
