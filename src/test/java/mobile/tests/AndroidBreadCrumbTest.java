package mobile.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import mobile.pages.AndroidAboutPage;
import mobile.pages.AndroidBreadCrumbMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class AndroidBreadCrumbTest
{
    private AppiumDriver driver;
    private static final int DEFAULT_SLEEP = 7000;

    @Before
    public void setUp() throws MalformedURLException
    {
        driver = createAndroidDriver();
    }

    @Test
    public void testAboutPageDirection()
    {
        AndroidBreadCrumbMenu androidBreadCrumbMenu = new AndroidBreadCrumbMenu(driver);
        androidBreadCrumbMenu.openBreadCrumbMenu.click();
        sleep();

        scrollBreadCrumb();
        scrollBreadCrumb();

        androidBreadCrumbMenu.about.click();
        sleep();

        AndroidAboutPage androidAboutPage = new AndroidAboutPage(driver);
        Assert.assertTrue(androidAboutPage.hakkinda.isDisplayed());
    }

    private AppiumDriver createAndroidDriver() throws MalformedURLException
    {
        File appDir = new File("/opt/sahibinden/");
        File app = new File(appDir, "app-debug.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //device configurations
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5");
        capabilities.setCapability("unicodeKeyboard", true);

        //app configurations
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.sahibinden");
        capabilities.setCapability("appActivity", "com.sahibinden.ui.supplementary.SplashScreenActivity");

        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("fastReset", true);
        capabilities.setCapability("clearSystemFiles", true);
        capabilities.setCapability("disableAndroidWatchers", true);
        capabilities.setCapability("noSign", true);
        //local config
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("newCommandTimeout", "120");


        URL url = new URL("http://127.0.01:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        if (null == driver)
            throw new NullPointerException("Android Driver is null...");

        return driver;
    }

    private void scrollBreadCrumb()
    {
        sleep();
        Dimension size = driver.manage().window().getSize();
        int startx = size.width / 5;
        int starty = (int) (size.width * 0.9);
        int endy = (int) (size.width * 0.1);
        touchLongPressAction(startx, starty, startx, endy, 500);
        sleep();
    }

    private void touchLongPressAction(int startX, int startY, int endX, int endY, int duration)
    {
        new TouchAction(driver).longPress(point(startX, startY)).moveTo(point(endX, endY))
                .waitAction(waitOptions(Duration.ofMillis(duration))).release().perform();
    }

    private static void sleep()
    {
        try
        {
            Thread.sleep(DEFAULT_SLEEP);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
