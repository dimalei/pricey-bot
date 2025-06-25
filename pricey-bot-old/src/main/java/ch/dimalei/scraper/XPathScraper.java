package ch.dimalei.scraper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class XPathScraper {

    public static String scrape(String url, String xpath) {

        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            HtmlPage page = webClient.getPage(url);

            String out = "";

            List<HtmlElement> elements = page.getByXPath(xpath);
            for (HtmlElement element : elements) {
                out += element.asXml();
            }

            return out;

        } catch (MalformedURLException e) {
            System.out.println("Bad URL:" + e.toString());
        } catch (FailingHttpStatusCodeException e) {
            System.out.println("Server error: " + e.getStatusCode());
        } catch (IOException e) {
            System.out.println("Something weng wrong: " + e.getLocalizedMessage());
        }

        return null;

    }
}