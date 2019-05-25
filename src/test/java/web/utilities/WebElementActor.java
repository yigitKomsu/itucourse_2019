package web.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Consumer;

public class WebElementActor
{
    private WebElementActor(WebElement target) {
        this.target = target;
    }

    private WebElement target;
    private WebElement waitFor;
    private TargetState targetState = TargetState.INVISIBLE;
    private WebDriver driver;
    private boolean condition = true;
    private long waitDuration = 0;
    private Consumer<WebElement> action = WebElement::click;

    public static WebElementActor target(WebElement element) {
        return new WebElementActor(element);
    }

    public WebElementActor when(boolean condition) {
        this.condition = condition;
        return this;
    }

    public WebElementActor action(Consumer<WebElement> consumer) {
        this.action = consumer;
        return this;
    }

    public WebElementActor waitFor(WebElement waitFor) {
        this.waitFor = waitFor;
        return this;
    }

    public WebElementActor waitFor(long duration) {
        this.waitDuration = duration;
        return this;
    }

    public WebElementActor becomes(TargetState targetState) {
        this.targetState = targetState;
        return this;
    }

    public WebElementActor using(WebDriver driver) {
        this.driver = driver;
        return this;
    }

    public void perform() throws InterruptedException
    {
        if (!this.condition) return;

        if (this.target == null) throw new IllegalArgumentException("Element to be clicked for must be defined using target(WebElement target).");
        if (this.driver == null) throw new IllegalArgumentException("Driver must be defined using using(WebDriver driver).");

        WebDriverWait wait = new WebDriverWait(this.driver, 10);

        // Try to move target into view by scrolling the whole window
        Rectangle targetRectangle = this.target.getRect();
        Dimension windowSize = this.driver.manage().window().getSize();
        Point targetPoint = new Point(targetRectangle.x, targetRectangle.y - windowSize.height / 2);
        Actions actions = new Actions(driver);
        actions.moveByOffset(targetPoint.x, targetPoint.y).perform();

        // If it is still not visible, make selenium do a best guess
        if (!this.target.isDisplayed())
        {
            actions.moveToElement(this.target).perform();
        }


        try
        {
            // Try to perform specified action, which is click by default
            this.action.accept(this.target);
        } catch (ElementNotInteractableException ex) {
            // If it is failed due to being not interactable (out of bounds or obstructed by another view)
            // try to move it to the center-left of the screen and try again

            // If this one fails too, there is some bigger problem at play
            ((JavascriptExecutor) this.driver).executeScript("window.scrollTo(arguments[0], arguments[1])", targetPoint.x, targetPoint.y);
            this.action.accept(this.target);
        }

        if (this.waitDuration > 0) {
            Thread.sleep(waitDuration);
        }

        if (this.waitFor == null) return;

        switch (this.targetState) {
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
