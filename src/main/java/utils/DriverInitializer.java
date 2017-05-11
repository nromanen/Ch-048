package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

;

/**
 * Created by radgast on 4/20/17.
 */
public class DriverInitializer {

    private DriverInitializer() {
    }

    private static final String FIREFOX_PROFILE_NAME = "default";

    private static final String FIREFOX_WEBDRIVER = "webdriver.gecko.driver";
    private static final String CHROME_WEBDRIVER = "webdriver.chrome.driver";
    private static final String WINDOWS_IE_WEBDRIVER = "webdriver.edge.driver";

    private static final String LINUX_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriverLinux";
    private static final String MAC_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriver";
    private static final String WINDOWS_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriver.exe";

    private static final String MAC_CHROME_WEBDRIVER_PATH = "src/main/resources/drivers/chromedriverMac";
    private static final String LINUX_CHROME_WEBDRIVER_PATH = "src/main/resources/drivers/chromedriverLinux";
    private static final String WINDOWS_CHROME_WEBDRIVER_PATH = "src/main/resources/drivers/chromedriver.exe";

    private static final String WINDOWS_IE_WEBDRIVER_PATH = "src/main/resources/drivers/MicrosoftWebDriver.exe";

    private static volatile WebDriver driver;

    private static void setSystemProperties() {
        String os = System.getProperty("os.name");
        switch (os) {
            case "Windows 10":
                System.setProperty(FIREFOX_WEBDRIVER, WINDOWS_FIREFOX_WEBDRIVER_PATH);
                System.setProperty(CHROME_WEBDRIVER, WINDOWS_CHROME_WEBDRIVER_PATH);
                System.setProperty(WINDOWS_IE_WEBDRIVER, WINDOWS_IE_WEBDRIVER_PATH);
                break;
            case "MacOS":
                System.setProperty(FIREFOX_WEBDRIVER, MAC_FIREFOX_WEBDRIVER_PATH);
                System.setProperty(CHROME_WEBDRIVER, MAC_CHROME_WEBDRIVER_PATH);
                break;
            case "Linux":
                System.setProperty(FIREFOX_WEBDRIVER, LINUX_FIREFOX_WEBDRIVER_PATH);
                System.setProperty(CHROME_WEBDRIVER, LINUX_CHROME_WEBDRIVER_PATH);
                break;
        }

    }

    public static void initialization() {

        setSystemProperties();

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile(FIREFOX_PROFILE_NAME);

        String browserType = System.getProperty("browser.name");
        System.out.println(browserType);

        switch (browserType) {
            case "firefox":
                driver = new FirefoxDriver(ffProfile);
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                DesiredCapabilities desiredCapabilities = DesiredCapabilities.edge();
                driver = new EdgeDriver(desiredCapabilities);
                break;
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public static synchronized WebDriver instance() {
        if (driver == null) {
            initialization();
            return driver;
        }
        return driver;
    }

    public static void getToUrl(String url) {
        instance().get(url);
    }

    public static void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        } else {
            System.out.println("Cant close session");
        }
    }

    public static void deleteAllCookies() {
        instance().manage().deleteAllCookies();
    }
}
