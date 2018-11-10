package com.test.scraper.parser;

import com.test.scraper.bean.ItemBean;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PageParser {
    List<ItemBean> extractItems(Document pageDocument) throws Exception;
}
