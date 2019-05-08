package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage
{

    public LoginPage(WebDriver browser)
    {
        super(browser);
    }

    @FindBy(id = "username")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "userLoginSubmitButton")
    public WebElement userLoginSubmitButton;

    @FindBy(css = ".qr-login-promotion .close")
    public WebElement qrPromotionModalCloseButton;
}
