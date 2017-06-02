package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This is a class which is used like wrapper for browser. It has methods which are used like adjusted waits and
 * utility methods which are used like helpers. Global wait in this class is set to 30 seconds
 *
 * @author Yri Tomko
 * @version 1.0
 */
public class BrowserWrapper {

    private static WebDriverWait wait = new WebDriverWait(DriverInitializer.instance(), 30, 250);

    /**
     *  This is a method which is used for getting title of the page
     * @return String version of page title
     */
    public static String getTitle() {
        return DriverInitializer.instance().getTitle();
    }

    /**
     * This is a method which is used for checking whether element present on the page.
     * It take WebElement like parameter and return boolean value according to result
     *
     * @param webElement It is a web element which we are checking
     * @return true if element present, false if element absent
     */
    public static boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * This method is wrapper for explicit wait until alert is present
     */
    public static void waitUntilAlertIsPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * This method is wrapper for explicit wait until web element is clickable
     * @param element element which is expected to be clickable
     */
    public static void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method is wrapper for explicit wait until element with given locator is clickable
     * @param  by locator which is expected to be clickable
     */
    public static void waitUntilElementClickableByLocator(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * This method is wrapper for explicit wait until web element is visible
     * @param element element which is expected to be visible
     */
    public static void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method is wrapper for explicit wait until element with given locator is present on the page
     * @param  locator locator which is expected to be present
     */
    public static void waitUntilElementIsPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * This method is wrapper for using selector. It takes selector like web element, and string with value to select
     * @param element It is a selector in  web element form
     * @param text It is a value which will be selected
     */
    public static void selectDropdown(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    /**
     * This is wrapper for sleep in current thread. It take number of seconds for which thread will be sleep
     * @param Seconds number of seconds for wait
     */
    public static void sleep(int Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilVisibleAndClickableAndNotStale(WebElement element){
        wait.until(ExpectedConditions.and(ExpectedConditions.elementToBeClickable(element),ExpectedConditions.visibilityOf(element),ExpectedConditions.not(ExpectedConditions.stalenessOf(element))));
    }

    /**
     * This method is wrapper for explicit wait until web element to be present text in element value
     * @param webElement    It is a selector in web element form
     * @param text  It is a value which will be presented
     */
    public static void waitUntilTextToBePresentInElementValue( WebElement webElement, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, text));
    }

    /**
     * This is wrapper for wait until page is load. It takes amount of time for wait
     *
     * @param time number of seconds for wait
     */
    public static void waitForPage(Long time) {
        DriverInitializer.instance().manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
    }

    /**
     * This is method for refreshing page
     */
    public static void refreshPage() {
        DriverInitializer.instance().navigate().refresh();
    }


    /**
     * This is a method which is used for checking whether aler is present on the page
     *
     * @return return true if alert present otherwise return false
     */
    public static boolean isAlertPresent() {
        WebDriverWait waitLocal = new WebDriverWait(DriverInitializer.instance(), 1);
        boolean foundAlert;
        try {
            waitLocal.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException e) {
            foundAlert = false;
        }
        return foundAlert;
    }

    /**
     * This is a method which is used for conformation alert if it is present
     */
    public static void confirmAlert() {
        waitUntilAlertIsPresent();
        Alert alert = DriverInitializer.instance().switchTo().alert();
        alert.dismiss();
    }

    /**
     * This is a method which is used for emulation double click using JavaScript. It takes element on which we click
     *
     * @param element it is element on which click is executed
     */
    public static void doubleClickJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverInitializer.instance();
        String doubleClickJS = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('dblclick',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('ondblclick');}window.stop();";
        js.executeScript(doubleClickJS, element);
    }

    /**
     * This is a method which is used for emulation triple click. It takes element on which we click
     *
     * @param element it is element on which click is executed
     */
    public static void tripleClick(WebElement element) {
        for (int i = 0; i < 3; i++) {
            clickWithStaleException(element);
        }
    }
    /**
     * This is a method which is used for emulation click on element which is prone to stale exception.
     * It takes element on which we click
     *
     * @param element it is element on which click is executed
     * @return if click is executed method return true otherwise return false
     */
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

    /**
     * This method is used for checking whether list is sorted
     *
     * @param list It is list which should be checked
     * @return ture if list sorted, otherwise return false
     */

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

    /**
     * This method is wrapper for explicit wait until element is not stale
     * @param element This is element for which we are wait
     */
    public static void waitUntilElementNotStale(WebElement element) {
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
    }
}
