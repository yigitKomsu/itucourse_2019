package mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidBreadCrumbMenu extends AndroidPageObject
{
    public AndroidBreadCrumbMenu(AppiumDriver driver)
    {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Gezinti bölmesini aç'] | //android.widget.ImageButton[@content-desc='Open the navigation pane'] | //android.view.ViewGroup[@resource-id='com.sahibinden:id/sahibinden_toolbar']/*[1] | //android.view.View[@resource-id='com.sahibinden:id/sahibinden_toolbar']/*[1]")
    public MobileElement openBreadCrumbMenu;

    @AndroidFindBy(xpath = "//android.widget.ListView//*[@text='Hakkında']")
    public MobileElement about;
}
