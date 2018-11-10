package com.test.scraper;

import com.test.scraper.parser.PageParser;
import com.test.scraper.utility.Fetcher;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ScraperTest {

    @Mock
    Fetcher fetcher;

    @Mock
    PageParser pageParser;

    @InjectMocks
    Scraper scraper;

    @Test
    public void testScraper() throws Exception {
        // given an URL
        when(fetcher.fetchDocument(anyString())).thenReturn(new Document(""));
        when(pageParser.extractItems(any())).thenReturn(new ArrayList<>());
        // when we call scape
        scraper.scrape("an URL");
        // then page parser will be called
        verify(pageParser, times(1)).extractItems(any(Document.class));
    }
}
