package web.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickWait {

    public enum Visibility {
        VISIBLE,
        INVISIBLE,
        CLICKABLE
    }

    private ClickWait(WebElement target) {
        this.target = target;
    }

    private WebElement target;
    private WebElement waitFor;
    private Visibility targetVisibility = Visibility.INVISIBLE;
    private WebDriver driver;

    public static ClickWait click(WebElement element) {
        return new ClickWait(element);
    }

    public ClickWait wait(WebElement waitFor) {
        this.waitFor = waitFor;
        return this;
    }

    public ClickWait becomes(Visibility visibility) {
        this.targetVisibility = visibility;
        return this;
    }

    public ClickWait using(WebDriver driver) {
        this.driver = driver;
        return this;
    }

    public void perform() {
        if (this.target == null) throw new IllegalArgumentException("Element to be clicked for must be defined using click(WebElement target).");
        if (this.waitFor == null) throw new IllegalArgumentException("Element to be waited for must be defined using wait(WebElement target).");
        if (this.driver == null) throw new IllegalArgumentException("Driver must be defined using using(WebDriver driver).");

        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        new Actions(driver).moveToElement(this.target).perform();
        this.target.click();
        switch (this.targetVisibility) {
            case VISIBLE:
                wait.until(ExpectedConditions.visibilityOf(this.waitFor));
                break;
            case INVISIBLE:
                wait.until(ExpectedConditions.invisibilityOf(this.waitFor));
                break;
            case CLICKABLE:
                wait.until(ExpectedConditions.elementToBeClickable(this.waitFor));
        }
    }
}
