package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Evgen on 11.04.2017.
 */
public class BrowserWrapper {

    private static WebDriverWait wait = new WebDriverWait(DriverInitializer.instance(), 30, 250);

    public static String getTitle() {
        return DriverInitializer.instance().getTitle();
    }

    public static boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void waitUntilAlertIsPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementClickableByLocator(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void selectDropdown(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    public static void sleep(int Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForPage(Long l) {
        DriverInitializer.instance().manage().timeouts().pageLoadTimeout(l, TimeUnit.SECONDS);
    }

    public static void refreshPage() {
        DriverInitializer.instance().navigate().refresh();
    }

    public static boolean isAlertPresent() {
        boolean foundAlert;
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException e) {
            foundAlert = false;
        }
        return foundAlert;
    }

    public static void conformAlert() {
        waitUntilAlertIsPresent();
        Alert alert = DriverInitializer.instance().switchTo().alert();
        alert.dismiss();
    }

    public static void doubleClickJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverInitializer.instance();
        String doubleClickJS = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('dblclick',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('ondblclick');}window.stop();";
        js.executeScript(doubleClickJS, element);
    }

    public static void tripleClick(WebElement element) {
        for (int i = 0; i < 3; i++) {
            clickWithStaleException(element);
        }

    }

    public static boolean clickWithStaleException(WebElement element) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 5) {
            try {
                element.click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public static boolean isSorted(List<String> list) {
        String previous = "";

        for (final String current : list) {
            if (current.compareTo(previous) < 0) {
                return false;
            }
            previous = current;
        }

        return true;
    }

    public static void waitUntilElementNotStale(WebElement element) {
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
    }
}
