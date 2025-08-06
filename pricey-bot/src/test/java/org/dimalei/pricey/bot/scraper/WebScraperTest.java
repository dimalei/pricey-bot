package org.dimalei.pricey.bot.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // System.out.println("HTML of " + url + "\n" + result);
    }

    @Test
    void testGetSourceExample() {
        url = "https://example.com";
        CompletableFuture<String> response = scraper.getSource(url);
        // block execution until result is here
        String result = response.join();
        assertNotNull(result);
        // System.out.println("HTML of " + url + "\n" + result);
    }

    @Test
    void testScrapeExampleCom() {
        Job exampleJob = new Job("example", 0, "https://example.com", "body > div:nth-child(1) > h1:nth-child(1)");
        CompletableFuture<String> response = scraper.scrapeJob(exampleJob);
        // block execution until result is here
        String result = response.join();
        assertEquals("Example Domain", result);
    }

    @Test
    void testScrapeDigitec() {
        Job exampleJob = new Job("example", 0,
                "https://www.digitec.ch/de/s1/product/philips-cx553511-46-db-ventilator-36507367",
                ".yhSYYIi5");
        CompletableFuture<String> response = scraper.scrapeJob(exampleJob);
        // block execution until result is here
        String result = response.join();

        Pattern price_pattern = Pattern.compile("CHF \\d+.\\d{2}");
        Matcher matcher = price_pattern.matcher(result);
        assertTrue(matcher.matches());
    }
}
