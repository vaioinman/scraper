package com.test.scraper.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/sainsburys.feature",
        glue={"com.test.scraper.stepdef"})
public class ScraperCucumberIntegrationTest {
}
