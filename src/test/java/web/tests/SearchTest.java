package web.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import web.pages.SearchPage;
import web.utilities.WebElementActor;
import web.utilities.TargetState;

public class SearchTest
{
    // Navigate to cars category and perform detailed search
    @Test
    public void testSearchWithDefaultConstraints() throws Exception
    {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.sahibinden.com/otomobil");

        performDetailedSearch(driver);

        driver.close();
    }

    // Navigate to cars section, perform detailed search
    // and then filter by keyword and select the top 2 results
    //
    // NOTICE: As of 25.05.2019, the constraints results in only ONE
    // result to be shown (there is only a single listing matching
    // all of the criteria). Therefore, navigating to the second top
    // result fails.
    @Test
    public void testSearchWithFilterKeyword() throws Exception {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.sahibinden.com/otomobil");

        SearchPage searchPage = performDetailedSearch(driver);

        // Expand the filter section if collapsed
        WebElementActor.target(searchPage.filterSection)
                .using(driver)
                .when(searchPage.filterSection.getAttribute("class").contains("collapseClosed"))
                .waitFor(searchPage.filterInput)
                .becomes(TargetState.VISIBLE)
                .perform();

        // Enter the filter keyword
        WebElementActor.target(searchPage.filterInput)
                .using(driver)
                .action(t -> t.sendKeys("boyasız"))
                .perform();

        // Apply the filter
        WebElementActor.target(searchPage.filterButton)
                .using(driver)
                .waitFor(searchPage.loading)
                .perform();

        // For each result, check that title contains the filter keyword
        for (WebElement result : searchPage.searchResults)
        {
            String title = result.findElement(By.xpath("./td[4]//a[contains(@class, 'classifiedTitle')]")).getText().trim().toLowerCase();
            Assert.assertTrue(title.contains("boyasız"));
        }

        // Click on the first result and wait 500ms
        WebElementActor.target(searchPage.searchResults.get(0))
                .using(driver)
                .waitFor(500)
                .perform();

        // Go back one page
        driver.navigate().back();

        // Click on the second result
        //
        // NOTICE: Please read the NOTICE on top of this test case.
        // Filtering results in only ONE matching listing.
        WebElementActor.target(searchPage.searchResults.get(1))
                .using(driver)
                .waitFor(500)
                .perform();

        driver.close();
    }

    // Input values, such as year, must not accept invalid characters, which
    // in this case are anything other than numbers
    @Test
    public void testSearchPreventInvalidCharacters() throws Exception {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.sahibinden.com/otomobil");

        SearchPage searchPage = new SearchPage(driver);

        WebElementActor.target(searchPage.yearSection)
                .using(driver)
                .when(searchPage.yearSection.getAttribute("class").contains("collapseClosed"))
                .waitFor(searchPage.minYear)
                .becomes(TargetState.VISIBLE)
                .perform();

        WebElementActor.target(searchPage.minYear)
                .using(driver)
                .action(e -> e.sendKeys("abcde-!"))
                .perform();

        Assert.assertNotEquals("abcde-!", searchPage.minYear.getAttribute("value"));

        driver.close();
    }

    // Input values must not accept negative values
    @Test
    public void testSearchPreventNegativeValues() throws Exception {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.sahibinden.com/otomobil");

        SearchPage searchPage = new SearchPage(driver);

        WebElementActor.target(searchPage.priceSection)
                .using(driver)
                .when(searchPage.priceSection.getAttribute("class").contains("collapseClosed"))
                .waitFor(searchPage.minPrice)
                .becomes(TargetState.VISIBLE)
                .perform();

        WebElementActor.target(searchPage.minPrice)
                .using(driver)
                .action(e -> e.sendKeys("-40000"))
                .perform();

        Assert.assertEquals("40.000", searchPage.minPrice.getAttribute("value"));

        driver.close();
    }


    // Price values must not accept way too big numbers
    @Test
    public void testSearchPreventHugeValues() throws Exception {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.sahibinden.com/otomobil");

        SearchPage searchPage = new SearchPage(driver);

        WebElementActor.target(searchPage.priceSection)
                .using(driver)
                .when(searchPage.priceSection.getAttribute("class").contains("collapseClosed"))
                .waitFor(searchPage.minPrice)
                .becomes(TargetState.VISIBLE)
                .perform();

        WebElementActor.target(searchPage.minPrice)
                .using(driver)
                .action(e -> e.sendKeys("123456789012345"))
                .perform();

        Assert.assertNotEquals("123456789012345", searchPage.minPrice.getAttribute("value"));
        Assert.assertTrue(searchPage.minPrice.getAttribute("value").length() < 15);

        driver.close();
    }

    // Perform a detailed search according to the criterion given in the homework
    private SearchPage performDetailedSearch(WebDriver driver) throws Exception {
        SearchPage searchPage = new SearchPage(driver);

        // Choose brand and wait for loading to disappear
        WebElementActor.target(searchPage.opelBrand)
                .using(driver)
                .waitFor(searchPage.loading)
                .perform();


        // Choose model and wait
        WebElementActor.target(searchPage.opelCorsaModel)
                .using(driver)
                .waitFor(searchPage.loading)
                .perform();


        // Select multiple models and wait for the animation to complete
        WebElementActor.target(searchPage.multipleModelButton)
                .using(driver)
                .waitFor(2000)
                .waitFor(searchPage.add2ndModelButton)
                .becomes(TargetState.CLICKABLE)
                .perform();

        // Add second model option
        WebElementActor.target(searchPage.add2ndModelButton)
                .using(driver)
                .waitFor(searchPage.secondModelTitle)
                .becomes(TargetState.VISIBLE)
                .perform();

        // Select second brand and wait
        WebElementActor.target(searchPage.renaultBrand)
                .using(driver)
                .waitFor(searchPage.loading)
                .perform();

        // Select model of the second brand
        WebElementActor.target(searchPage.renaultClioModel)
                .using(driver)
                .waitFor(searchPage.loading)
                .perform();

        // Check that desired models are selected
        Assert.assertEquals("Opel Corsa", searchPage.firstModelTitle.getAttribute("title"));
        Assert.assertEquals("Renault Clio", searchPage.secondModelTitle.getAttribute("title"));

        // Expand the KM section if it is not expanded
        WebElementActor.target(searchPage.kmSection)
                .using(driver)
                .when(searchPage.kmSection.getAttribute("class").contains("collapseClosed"))
                .waitFor(searchPage.maxKm)
                .becomes(TargetState.VISIBLE)
                .perform();

        // Enter the max KM value
        WebElementActor.target(searchPage.maxKm)
                .using(driver)
                .action(t -> t.sendKeys("50000"))
                .perform();

        // Check that entered value is correct
        Assert.assertEquals("50.000", searchPage.maxKm.getAttribute("value"));


        // Expand price section if it is collapsed
        WebElementActor.target(searchPage.priceSection)
                .using(driver)
                .when(searchPage.priceSection.getAttribute("class").contains("collapseClosed"))
                .waitFor(searchPage.minPrice)
                .becomes(TargetState.VISIBLE)
                .perform();

        // Enter the minimum price amount
        WebElementActor.target(searchPage.minPrice)
                .using(driver)
                .action(t -> t.sendKeys("40000"))
                .perform();


        // Enter th maximum price amount
        WebElementActor.target(searchPage.maxPrice)
                .using(driver)
                .action(t -> t.sendKeys("50000"))
                .perform();

        // Check that both min and max prices are correctly entered
        Assert.assertEquals("40.000", searchPage.minPrice.getAttribute("value"));
        Assert.assertEquals("50.000", searchPage.maxPrice.getAttribute("value"));

        // Expand the year section if collapsed
        WebElementActor.target(searchPage.yearSection)
                .using(driver)
                .when(searchPage.yearSection.getAttribute("class").contains("collapseClosed"))
                .waitFor(searchPage.minYear)
                .becomes(TargetState.VISIBLE)
                .perform();

        // Enter the min year value
        WebElementActor.target(searchPage.minYear)
                .using(driver)
                .action(t -> t.sendKeys("2013"))
                .perform();

        // Enter the max year value
        WebElementActor.target(searchPage.maxYear)
                .using(driver)
                .action(t -> t.sendKeys("2015"))
                .perform();

        // Check that entered year values are correct
        Assert.assertEquals("2013", searchPage.minYear.getAttribute("value"));
        Assert.assertEquals("2015", searchPage.maxYear.getAttribute("value"));

        // Perform the search
        WebElementActor.target(searchPage.searchButton)
                .using(driver)
                .waitFor(searchPage.loading)
                .perform();

        // Order by year ascending by clicking the year column
        WebElementActor.target(searchPage.yearColumn)
                .using(driver)
                .waitFor(searchPage.loading)
                .perform();

        // Order by year descending by clicking the year column again
        WebElementActor.target(searchPage.yearColumn)
                .using(driver)
                .waitFor(searchPage.loading)
                .perform();

        // Check whether results are sorted descending by year
        searchPage.searchResults.stream()
                .map(element -> Integer.parseInt(element.findElement(By.xpath("./td[5]")).getText().trim()))
                .reduce((left, right) -> {
                    Assert.assertTrue(left >= right);
                    return right;
                });

        // For each result check:
        //      - Year is in the correct range
        //      - Price is in the correct range
        for (WebElement result : searchPage.searchResults)
        {
            int year = Integer.parseInt(result.findElement(By.xpath("./td[5]")).getText().trim());
            Assert.assertTrue(year >= 2013 && year <= 2015);

            int price = Integer.parseInt(result.findElement(By.xpath("./td[8]/div"))
                    .getText()
                    .trim()
                    .split(" ")[0]
                    .replace(".", ""));
            Assert.assertTrue(price >= 40000 && price <= 50000);
        }

        // Return the page
        return searchPage;
    }
}