package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends AbstractPage
{
    public SearchPage(WebDriver browser)
    {
        super(browser);
    }

    @FindBy(name = "price_min")
    public WebElement MinPriceInput;

    @FindBy(name = "price_max")
    public WebElement MaxPriceInput;

    @FindBy(name = "a4_max")
    public WebElement MaxKilometerInput;

    @FindBy(name = "a5_min")
    public WebElement MinYearInput;

    @FindBy(name = "a5_max")
    public WebElement MaxYearInput;

    @FindBy(id = "searchResultSorter")
    public WebElement SearchResultSorter;

    @FindBy(name = "query_text")
    public WebElement QueryText;
}