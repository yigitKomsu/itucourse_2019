package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SearchPage extends AbstractPage
{
    public SearchPage(WebDriver browser)
    {
        super(browser);
    }


    @FindBy(xpath = "//div[@class='opening']")
    public WebElement loading;

    @FindBy(xpath = "//dt[@id = '_cllpsID_price' and contains(@class, 'collapseTitle')]")
    public WebElement priceSection;

    @FindBy(name = "price_min")
    public WebElement minPrice;

    @FindBy(name = "price_max")
    public WebElement maxPrice;

    @FindBy(xpath = "//dt[@id = '_cllpsID_a4' and contains(@class, 'collapseTitle')]")
    public WebElement kmSection;

    @FindBy(name = "a4_max")
    public WebElement maxKm;

    @FindBy(xpath = "//dt[@id = '_cllpsID_a5' and contains(@class, 'collapseTitle')]")
    public WebElement yearSection;

    @FindBy(name = "a5_min")
    public WebElement minYear;

    @FindBy(name = "a5_max")
    public WebElement maxYear;

    @FindBy(xpath = "//dt[@id = '_cllpsID_filter-by-keyword' and contains(@class, 'collapseTitle')]")
    public WebElement filterSection;

    @FindBy(xpath = "//input[@name='query_text' and contains(@class, 'filterKeyword')]")
    public WebElement filterInput;

    @FindBy(xpath = "//button[contains(@class, 'filterByKeywordSubmit')]")
    public WebElement filterButton;

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

    @FindBy(xpath = "//button[contains(@class, 'btn') and contains(@class, 'search-submit')]")
    public WebElement searchButton;

    @FindBy(xpath = "//table[@id='searchResultsTable']//td/a[normalize-space(text())='Yıl']")
    public WebElement yearColumn;

    @FindBy(xpath = "//table[@id='searchResultsTable']/tbody/tr[@data-id]")
    public List<WebElement> searchResults;
}