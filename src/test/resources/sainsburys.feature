Feature: scraping Sainsbury's web pages for grocery items
    Scenario: scraping Sainsbury
        Given a Sainsbury web page containing grocery listing
        When user provide the URL of that web page
        Then scraper will return a JSON report of items found on that page
