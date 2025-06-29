package org.dimalei.pricey.bot.scraper;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import org.dimalei.pricey.bot.model.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWebScraper implements WebScraper {

    @Override
    public CompletableFuture<String> scrape(Job scrapeJob) {

        return CompletableFuture.supplyAsync(() -> {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            WebDriver driver = new ChromeDriver(options);
            String out = null;
            By target = By.xpath(scrapeJob.getTargetProperty());
            // By target = By.className(scrapeJob.getTargetProperty());
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            try {
                driver.get(scrapeJob.getUrl());
                wait.until(ExpectedConditions.visibilityOfElementLocated(target));
                WebElement element = driver.findElement(target);
                out = element.getText();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }

            return out;
        });

    }

}
