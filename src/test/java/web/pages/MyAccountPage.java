package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends AbstractPage
{
    public MyAccountPage(WebDriver browser)
    {
        super(browser);
    }

    @FindBy(id = "usernameArea")
    public WebElement userNameArea;
}
