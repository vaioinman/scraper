package com.test.scraper.integration;

import com.test.scraper.TestApplicationConfiguration;
import com.test.scraper.bean.ItemBean;
import com.test.scraper.parser.PageParser;
import com.test.scraper.parser.PageParserImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class IntegrationTest {

    @Autowired
    private PageParser pageParser;


    @Test
    public void integrationTest() throws Exception {
        List<ItemBean> items = pageParser.extractItems(getProductPageDocument());

        assertThat(items, hasSize(12));
    }

    private Document getProductPageDocument() throws IOException {
        return Jsoup.connect("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html").get();
    }
}
