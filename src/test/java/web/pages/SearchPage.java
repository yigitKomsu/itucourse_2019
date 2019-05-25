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


    @FindBy(xpath = "//div[@class='opening']")
    public WebElement loading;

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

    @FindBy(xpath = "//div[@class='multiple-model-wrapper']")
    public WebElement multipleModelPane;

    @FindBy(xpath = "//div[@id='searchCategoryContainer']//ul/li[@class='cl2']/a[normalize-space(text())='Opel']")
    public WebElement opelBrand;

    @FindBy(xpath = "//div[@id='searchCategoryContainer']//ul/li[@class='cl3']/a[normalize-space(text())='Corsa']")
    public WebElement opelCorsaModel;

    @FindBy(xpath = "//div[@class='enable-multiple-model-selection']")
    public WebElement multipleModelButton;

    @FindBy(xpath = "//div[@class='multiple-models']/div[@data-index=2]")
    public WebElement add2ndModelButton;

    @FindBy(xpath = "//div[@class='multiple-models']/div[@data-index=2]//li[@class='cl2']/a[normalize-space(text())='Renault']")
    public WebElement renaultBrand;

    @FindBy(xpath = "//div[@class='multiple-models']/div[@data-index=2]//li[@class='cl3']/a[normalize-space(text())='Clio']")
    public WebElement renaultClioModel;

    @FindBy(xpath = "//div[@class='multiple-models']/div[@data-index=1]//div[@class='model-header']")
    public WebElement firstModelTitle;


    @FindBy(xpath = "//div[@class='multiple-models']/div[@data-index=2]//div[@class='model-header']")
    public WebElement secondModelTitle;
}