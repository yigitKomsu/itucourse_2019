package web.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.pages.SearchPage;
import web.pages.MyAccountPage;

public class SearchTest
{
    @Test
    public void testSearch() throws Exception
    {
        WebDriver driver = new ChromeDriver();

        // Category for opel corsa
        driver.get("https://www.sahibinden.com/arama/detayli?category=23582");

        SearchPage page = new SearchPage();
    }
}