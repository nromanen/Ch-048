package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Evgen on 11.04.2017.
 */
public class BrowserWrapper {

    private static final String FIREFOX_PROFILE_NAME = "default";
    private static final String WEBDRIVER_NAME = "webdriver.gecko.driver";
    private static final String LINUX_WEBDRIVER_PATH = "src/main/resources/geckodriver";
    private static final String MACOS_WEBDRIVER_PATH = "src/main/resources/geckodriver";
    private static final String WEBDRIVER_PATH = "src/main/resources/geckodriver.exe";
    private static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";

    private static WebDriverWait wait;


    public static WebDriver browserInitialization() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile(FIREFOX_PROFILE_NAME);
        ffProfile.setAcceptUntrustedCertificates(true);
        ffProfile.setAssumeUntrustedCertificateIssuer(false);
        String osName = System.getProperty("os.name");
        switch (osName){
            case "Linux":
                System.setProperty(WEBDRIVER_NAME, LINUX_WEBDRIVER_PATH);
                break;
            case "Windows 10":
                System.setProperty(WEBDRIVER_NAME, WEBDRIVER_PATH);
             break;
            case "MacOS":
                 System.setProperty(WEBDRIVER_NAME, MACOS_WEBDRIVER_PATH);
                break;
        }
        WebDriver driver = new FirefoxDriver(ffProfile);
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30, 250);
        return driver;
    }


    public static void browserClose(WebDriver driver) {
        driver.quit();
    }


    public static void pageClose(WebDriver driver){
        driver.close();
    }


    public static boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public static boolean isElementEnable(WebElement webElement) {
        try {
            return webElement.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public static void waitUntilAlertIsPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }


    public static void waitUntilElementSelectionState(WebElement element, boolean bool) {
        wait.until(ExpectedConditions.elementSelectionStateToBe(element, bool));
    }


    public static void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementClickableByLocator(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }


    public static void waitUntilElementSelected(WebElement element) {
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }


    public static void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void waitUntilElementInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


    public static void waitUntilTitleContains(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }


    public static void waitUntilAllVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }


    public static void waitUntilElementIsPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public static void waitUntilUrlAvaliable(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
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





    public static void waitForPage(WebDriver driver){
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public static void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public static boolean isAlertPresent(WebDriver driver)
    {
        try
        {
           driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException Ex)
        {
            return false;
        }
    }
    public static void conformAlert(WebDriver driver){
        waitUntilAlertIsPresent();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }


    public static void dragdrop(WebElement LocatorFrom, String xto, String yto , WebDriver driver) {
        ((JavascriptExecutor)driver).executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
                        "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
                LocatorFrom,xto,yto);
    }
}
