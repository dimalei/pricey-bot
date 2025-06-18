package ch.dimalei.scraper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class XPathScraperTest {
    final String url = "https://www.baechli-bergsport.ch/de/schuhe/damenschuhe/kletterschuhe/la-sportiva-54124-python";
    final String xpath = "/html/body/main/div[1]/div[1]/div[2]/article[2]/div[1]/form/div[4]/span[2]";

    @Test
    void testScrape() {
        String result = XPathScraper.scrape(url, xpath);
        assertNotNull(result);
        System.out.println(result);
    }
}
