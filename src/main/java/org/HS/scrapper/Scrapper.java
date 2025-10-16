package org.HS.scrapper;

import com.microsoft.playwright.*;
import org.HS.data.AddressData;
import org.HS.data.PersonalInformation;
import org.HS.kafkaProd.ProducerInformation;

import java.util.List;

public class Scrapper {
    private ProducerInformation producerInformation;

    public  Scrapper(){
        producerInformation = new ProducerInformation();
    }
    public void scrape() {
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

           page.locator("//form/button").nth(2)
                    .click();

           Locator element = page.locator(".searchhit-result a");
           //System.out.println(element.first().textContent());
           element.first().click();
           Locator info = page.locator(".col-md-3 p");
           String[] addressInfo = info.first().innerHTML().split("<br>");
           AddressData addressData = new AddressData(addressInfo[1],addressInfo[2].split("")[1],
                   addressInfo[3], Integer.parseInt(addressInfo[2].split("")[0].replace("D-","")));
           String[] personalInfo = info.nth(1).innerHTML().split("<br>");
           Locator serviceDescription = page.locator(".col-md-6 p");
           List<String> services  = List.of(serviceDescription.first().textContent().split(", "));
           Locator registeredProfession = page.locator(".col-md-12 p");
            List<String> registeredProfessions = List.of(registeredProfession.innerHTML().split("<br>"));
           PersonalInformation personalInformation = new PersonalInformation(personalInfo[0],
                   Integer.parseInt(personalInfo[1]),personalInfo[3],addressData,registeredProfessions,services);
           System.out.println(info.first().innerHTML());

           producerInformation.send("handwerker",personalInformation);



           //System.out.println(element.first().textContent());
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
