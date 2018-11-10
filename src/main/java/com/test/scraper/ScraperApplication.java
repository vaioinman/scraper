package com.test.scraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class ScraperApplication implements CommandLineRunner {
	@Autowired
	private Scraper scraper;

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
            String resultJSON = scraper.scrape(args[0]);

            System.out.println("Scraping is done. Showing result in JSON...\n");
            System.out.println(resultJSON);

            System.out.println("----------------------- END ----------------------");
        } catch (Exception e) {
            System.err.println("Error found while scraping. Printing stacktrace...");
            e.printStackTrace();
        }
    }
}
