package com.test.scraper;

import com.test.scraper.bean.ItemBean;
import com.test.scraper.parser.PageParser;
import com.test.scraper.parser.PageParserImpl;
import com.test.scraper.utility.ItemsToJsonStringConverter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
class ScraperApplication implements CommandLineRunner {
	@Autowired
	private PageParser pageParser;

	public static void main(String[] args) {
		SpringApplication.run(ScraperApplication.class, args);
	}

    @Override
    public void run(String... args) {
        if (args.length != 1) {
            System.out.println("Run program again with an URL to scrape.");
            System.exit(1);
        }

        System.out.println("----------------- Grocery Scraper -----------------");

        try {
            Document pageDocument = Jsoup.connect(args[0]).get();
            System.out.println("Valid URL is found. Scraping...\n");
            List<ItemBean> itemBeans = pageParser.extractItems(pageDocument);

            System.out.println("Scraping is done. Showing result in JSON...\n");
            String resultJSON = ItemsToJsonStringConverter.convertToJsonString(itemBeans);

            System.out.println(resultJSON);

            System.out.println("----------------------- END ----------------------");
        } catch (Exception e) {
            System.err.println("Error found while scraping. Printing stacktrace...");
            e.printStackTrace();
        }
    }
}
