package org.dimalei.pricey.bot.scraper;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import org.dimalei.pricey.bot.model.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWebScraper implements WebScraper {

    static final String CHROME_ARGUMENTS[] = {
            "--headless=new",
            "--disable-gpu",
            "--window-size=1920,1080",
            "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36",
            "--enable-features=NetworkService,NetworkServiceInProcess",
            "--enable-quic",
            "--ignore-certificate-errors",
            "--no-sandbox"
    };
    static final Duration TIMEOUT = Duration.ofMillis(1000);

    @Override
    public CompletableFuture<String> scrapeJob(Job scrapeJob) {

        return CompletableFuture.supplyAsync(() -> {

            WebDriver driver = createWebDriver();
            String out = null;
            By target = By.cssSelector(scrapeJob.getTargetProperty());
            Wait<WebDriver> wait = new WebDriverWait(driver, TIMEOUT);

            try {
                driver.get(scrapeJob.getUrl());
                wait.until(ExpectedConditions.visibilityOfElementLocated(target));
                WebElement element = driver.findElement(target);
                out = element.getText().replace("\n", " ");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }

            return out;
        });

    }

    @Override
    public CompletableFuture<String> getSource(String url) {
        return CompletableFuture.supplyAsync(() -> {
            WebDriver driver = createWebDriver();
            Wait<WebDriver> wait = new WebDriverWait(driver, TIMEOUT);

            try {
                driver.get(url);
                wait.until(
                        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                                .equals("complete"));

                return driver.getPageSource();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.close();
            }

            return null;
        });
    }

    private static WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(CHROME_ARGUMENTS);
        return new ChromeDriver(options);
    }

}
