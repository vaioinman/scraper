package com.test.scraper.integration;

import com.test.scraper.Scraper;
import com.test.scraper.TestApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class IntegrationTest {

    @Autowired
    private Scraper scraper;

    @Test
    public void integrationTest() throws Exception {
        String resultJSON = scraper.scrape("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html");

        assertThat(resultJSON, not(isEmptyOrNullString()));
        assertThat(resultJSON, containsString("Sainsbury's Strawberries 400g"));
        assertThat(resultJSON, containsString("1.75"));
        assertThat(resultJSON, containsString("33"));
        assertThat(resultJSON, containsString("by Sainsbury's strawberries"));

        assertThat(resultJSON, containsString("Sainsbury's Redcurrants 150g"));
        assertThat(resultJSON, containsString("2.5"));
        assertThat(resultJSON, containsString("71"));
        assertThat(resultJSON, containsString("by Sainsbury's redcurrants"));
    }
}
