package utils;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * This is class which is used for initialization webdriver of proper browser. It's design according to singleton
 * pattern, which mean initialization of browser occurs only for the first calling
 * It chose OS properties according to system properties. It chose proper browser according to properties
 * which is passed from Maven. This class also provides ability to moving for page and cleaning cookies
 *
 * @author Yuri Tomko, Bogdan Rymar
 * @version 1.0
 */
public class DriverInitializer {

    private DriverInitializer() {
    }

    private static final String FIREFOX_WEBDRIVER = "webdriver.gecko.driver";
    private static final String CHROME_WEBDRIVER = "webdriver.chrome.driver";
    private static final String WINDOWS_IE_WEBDRIVER = "webdriver.edge.driver";

    private static final String LINUX_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriverLinux";
    private static final String MAC_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriverMac";
    private static final String WINDOWS_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriver.exe";

    private static final String MAC_CHROME_WEBDRIVER_PATH = "src/main/resources/drivers/chromedriverMac";
    private static final String LINUX_CHROME_WEBDRIVER_PATH = "src/main/resources/drivers/chromedriverLinux";
    private static final String WINDOWS_CHROME_WEBDRIVER_PATH = "src/main/resources/drivers/chromedriver.exe";

    private static final String WINDOWS_IE_WEBDRIVER_PATH = "src/main/resources/drivers/MicrosoftWebDriver.exe";

    private static volatile WebDriver driver;

    /**
     * This is a method which is used for setting os properties according to Operation System
     */
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

    /**
     * This is a method main goal of which is to initialize web driver with proper browser, name of which
     * is passed from Maven CL. This method also set implicit wait for all framework
     */
    public static void initialization() throws MalformedURLException {
        setSystemProperties();
        ProfilesIni profile = new ProfilesIni();
        DesiredCapabilities dc = DesiredCapabilities.firefox();
        dc.setCapability(FirefoxDriver.PROFILE, profile);
        dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        String browserType = System.getProperty("browser.name");
        System.out.println(browserType);

        switch (browserType) {
            case "firefox":
                driver = new FirefoxDriver(dc);
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                DesiredCapabilities desiredCapabilities = DesiredCapabilities.edge();
                driver = new EdgeDriver(desiredCapabilities);
                break;
            case "phantomjs":
                ArrayList<String> cliArgsCap = new ArrayList<>();
                DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
                cliArgsCap.add("--web-security=false");
                cliArgsCap.add("--ssl-protocol=any");
                cliArgsCap.add("--ignore-ssl-errors=true");
                capabilities.setCapability("takesScreenshot", true);
                capabilities.setCapability(
                        PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
                capabilities.setCapability(
                        PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
                        new String[] { "--logLevel=2" });
                driver = new PhantomJSDriver(capabilities);
                driver.manage().window().setSize(new Dimension(1024,768));
                break;
            case "jBrowserDriver":
                driver = new JBrowserDriver(Settings.builder().
                        timezone(Timezone.EUROPE_KIEV)
                        .ssl("trustanything")
                        .javaOptions("-Djsse.enableSNIExtension=false")
                        .hostnameVerification(false)
                        .blockAds(false)
                        .cache(true)
                        .userAgent(UserAgent.TOR)
                        .headless(true)
                        .javascript(true).build());
                break;
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    /**
     * This  method  return instance of Web Driver. If it is a first call of method it create instance
     * of webdriver, otherwise in return already created instance. This method is synchronized.
     * @return
     */
    public static synchronized WebDriver instance() {
        if (driver == null) {
            try {
                initialization();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
        return driver;
    }

    /**
     * This is a method which provide ability of moving to given url
     *
     * @param url string representation of target page url
     */
    public static void getToUrl(String url) {
        instance().get(url);
    }

    /**
     * This is a method which is provide closing of browser after test execution
     */
    public static void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        } else {
            System.out.println("Cant close session");
        }
    }

    /**
     *  This is method which is used for cleaning cookies after closing browser window
     */
    public static void deleteAllCookies() {
        instance().manage().deleteAllCookies();
    }
}
