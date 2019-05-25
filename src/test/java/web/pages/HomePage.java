package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HomePage extends AbstractPage
{

    public HomePage(WebDriver browser)
    {
        super(browser);
    }

    @FindBy(xpath = "//ul[@class='categories-left-menu']//a[contains(text(), 'Otomobil')]")
    public WebElement OtomobilCategory;

    @FindBy(id = "closeCookiePolicy")
    public WebElement closeCookiePolicy;


}

