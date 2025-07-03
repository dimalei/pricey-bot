package org.dimalei.pricey.bot.scraper;

import java.util.concurrent.CompletableFuture;

import org.dimalei.pricey.bot.model.Job;

public interface WebScraper {
    public CompletableFuture<String> scrapeJob(Job scrapeJob);

    public CompletableFuture<String> getSource(String url);
}
