package web.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.CategoryPage;
import web.pages.HomePage;
import web.pages.SearchPage;
import web.pages.MyAccountPage;
import web.utilities.ClickWait;

public class SearchTest
{
    @Test
    public void testSearch() throws Exception
    {
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://www.sahibinden.com/otomobil");

        Actions actions = new Actions(driver);
        SearchPage searchPage = new SearchPage(driver);

        ClickWait.click(searchPage.opelBrand)
                .using(driver)
                .wait(searchPage.loading)
                .perform();


        ClickWait.click(searchPage.opelCorsaModel)
                .using(driver)
                .wait(searchPage.loading)
                .perform();


        ClickWait.click(searchPage.multipleModelButton)
                .using(driver)
                .wait(searchPage.add2ndModelButton)
                .becomes(ClickWait.Visibility.CLICKABLE)
                .perform();


        ClickWait.click(searchPage.add2ndModelButton)
                .using(driver)
                .wait(searchPage.loading)
                .perform();


        ClickWait.click(searchPage.renaultBrand)
                .using(driver)
                .wait(searchPage.loading)
                .perform();


        ClickWait.click(searchPage.renaultClioModel)
                .using(driver)
                .wait(searchPage.loading)
                .perform();


        Assert.assertEquals("Opel Corsa", searchPage.firstModelTitle.getAttribute("title"));
        Assert.assertEquals("Renault Clio", searchPage.secondModelTitle.getAttribute("title"));

        driver.close();
    }

    private enum TargetVisibility
    {
        VISIBLE,
        INVISIBLE
    }

    private void clickAndWait(WebElement element, WebDriver driver, WebElement loader, TargetVisibility visibility)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        new Actions(driver).moveToElement(element).perform();
        element.click();
        switch (visibility)
        {
            case VISIBLE:
                wait.until(ExpectedConditions.visibilityOf(loader));
                break;
            case INVISIBLE:
                wait.until(ExpectedConditions.invisibilityOf(loader));
                break;
        }
    }


    private CategoryPage navigateToCategory(WebDriver driver, Actions actions, HomePage page) throws Exception
    {
        if (page.closeCookiePolicy != null)
        {
            page.closeCookiePolicy.click();

            Thread.sleep(500);
        }

        actions.moveToElement(page.OtomobilCategory);
        page.OtomobilCategory.click();
        Thread.sleep(500);

        Assert.assertEquals("https://www.sahibinden.com/kategori/otomobil", driver.getCurrentUrl());

        return new CategoryPage(driver);
    }

    private SearchPage navigateToSearch(WebDriver driver, Actions actions, CategoryPage categoryPage) throws Exception
    {
        categoryPage.allPostings.click();
        Thread.sleep(500);

        Assert.assertEquals("https://www.sahibinden.com/otomobil", driver.getCurrentUrl());
        return new SearchPage(driver);
    }
}