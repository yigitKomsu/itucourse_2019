package web.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.pages.LoginPage;
import web.pages.MyAccountPage;

public class LoginTest
{
    @Test
    public void testLogin() throws Exception
    {
        // Optional, if not specified, WebDriver will search your path for chromedriver.
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

        // initialize webdriver
        WebDriver driver = new ChromeDriver();

        //Go to login page
        driver.get("https://secure.sahibinden.com/giris");
        Thread.sleep(500);

        //create loginPage
        LoginPage loginPage = new LoginPage(driver);

        //close QR popup
        loginPage.qrPromotionModalCloseButton.click();
        Thread.sleep(500);

        //input for username
        loginPage.usernameInput.sendKeys("muratmerc@gmail.com");
        Thread.sleep(500);

        //input for password (ie: wrong password)
        loginPage.passwordInput.sendKeys("24amamamamasd");
        Thread.sleep(500);

        //click login button
        loginPage.userLoginSubmitButton.click();
        Thread.sleep(500);

        //crate myAccountPage
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        //Assertion statement
        Assert.assertTrue(myAccountPage.userNameArea.isDisplayed());

        //closing webdriver instance
        driver.close();
    }
}
