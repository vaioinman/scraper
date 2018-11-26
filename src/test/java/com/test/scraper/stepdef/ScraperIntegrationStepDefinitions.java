package com.test.scraper.stepdef;

import com.test.scraper.Scraper;
import com.test.scraper.integration.SpringBootBaseIntegrationTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.StringContains;
import org.springframework.beans.factory.annotation.Autowired;

public class ScraperIntegrationStepDefinitions extends SpringBootBaseIntegrationTest {
    String url;

    @Autowired
    Scraper scraper;

    String result;

    @Given("^a Sainsbury web page containing grocery listing$")
    public void aSainsburyWebPageContainingGroceryListing() throws Throwable {
        url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
    }

    @When("^user provide the URL of that web page$")
    public void userProvideTheURLOfThatWebPage() throws Throwable {
        result = scraper.scrape(url);
    }

    @Then("^scraper will return a JSON report of items found on that page$")
    public void scraperWillReturnAJSONReportOfItemsFoundOnThatPage() throws Throwable {
        MatcherAssert.assertThat(result, StringContains.containsString("Sainsbury's Strawberries 400g"));
    }
}
