package com.test.scraper.integration;

import com.test.scraper.TestApplicationConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(
        classes = TestApplicationConfiguration.class)
public abstract class SpringBootBaseIntegrationTest {

}
