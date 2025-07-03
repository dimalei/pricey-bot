package org.dimalei.pricey.bot.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.CompletableFuture;
import org.dimalei.pricey.bot.model.Job;
import org.junit.jupiter.api.Test;

public class WebScraperTest {
    private WebScraper scraper = new SeleniumWebScraper();
    private String url;

    @Test
    void testGetSourceDigitec() {
        url = "https://www.digitec.ch/de/s1/product/philips-cx553511-46-db-ventilator-36507367";
        CompletableFuture<String> response = scraper.getSource(url);
        // block execution until result is here
        String result = response.join();
        assertNotNull(result);
        System.out.println("HTML of " + url + "\n" + result);
    }

    @Test
    void testGetSourceExample() {
        url = "https://example.com";
        CompletableFuture<String> response = scraper.getSource(url);
        // block execution until result is here
        String result = response.join();
        assertNotNull(result);
        System.out.println("HTML of " + url + "\n" + result);
    }

    @Test
    void testScrapeExampleCom() {
        Job exampleJob = new Job("example", 0, "https://example.com", "/html/body/div/h1");
        CompletableFuture<String> response = scraper.scrapeJob(exampleJob);
        // block execution until result is here
        String result = response.join();
        assertEquals("Example Domain", result);
    }

    @Test
    void testScrapeDigitec() {
        Job exampleJob = new Job("example", 0,
                "https://www.digitec.ch/de/s1/product/philips-cx553511-46-db-ventilator-36507367",
                "/html/body/div/div[2]/div[2]/div/div/main/div/div[1]/div/div/div[2]/div/div[1]/span/strong/button");
        CompletableFuture<String> response = scraper.scrapeJob(exampleJob);
        // block execution until result is here
        String result = response.join();
        System.out.println(result);
        assertEquals("CHF 89.90", result);
    }
}
