package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends AbstractPage
{
    public CategoryPage(WebDriver browser)
    {
        super(browser);
    }

    @FindBy(xpath = "//a[@href = '/otomobil']")
    public WebElement allPostings;
}
