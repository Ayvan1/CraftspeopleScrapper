package org.HS.scrapper;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class Scrapper {

    public static void main(String[] args) {
        try(Playwright playwright = Playwright.create()) {
            Browser browser =  playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();
            page.navigate("https://www.hwk-schwaben.de/71,1125,bdbsearch.html");
            Locator acceptCookies = page.locator("text='Alles akzeptieren'");
            if (acceptCookies.isVisible()) {
                acceptCookies.click();
            }
            Thread.sleep(100);
            page.locator("#search-filter-zipcode").pressSequentially("1");
            Thread.sleep(100);
            page.locator("#search-slider")
                    .evaluate("element => { element.value = '1'; " +
                            "element.dispatchEvent(new Event('input', { bubbles: true })); " +
                            "element.dispatchEvent(new Event('change', { bubbles: true })); }");
            System.out.println("1");
           page.locator("//form/button").nth(2)
                    .click();
           System.out.println("2");
           Locator element = page.locator(".searchhit-result a");

           element.first().click();
           Locator info = page.locator(".col-md-3 p");
           System.out.println(info.first().innerHTML());
           //System.out.println(element.first().textContent());
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
