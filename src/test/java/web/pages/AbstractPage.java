package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage
{
    protected WebDriver browser;

    protected AbstractPage(WebDriver browser)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        PageFactory.initElements(browser, this);
    }
}
