package org.dimalei.pricey.bot.service;

import org.dimalei.pricey.bot.scraper.WebScraper;
import org.springframework.stereotype.Service;

@Service
public class WebScraperService {

    private final WebScraper webScraper;

    public WebScraperService(WebScraper webScraper) {
        this.webScraper = webScraper;
    }

}
