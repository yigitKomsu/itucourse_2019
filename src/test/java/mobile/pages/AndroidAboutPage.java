package mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAboutPage extends AndroidPageObject
{
    public AndroidAboutPage(AppiumDriver driver)
    {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='HAKKINDA']")
    public MobileElement hakkinda;
}
