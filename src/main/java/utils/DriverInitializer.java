package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.Extension;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by radgast on 4/20/17.
 */
public class DriverInitializer {

    private DriverInitializer(){}

    private static final String FIREFOX_PROFILE_NAME = "default";

    private static final String FIREFOX_WEBDRIVER = "webdriver.gecko.driver";
    private static final String CHROME_WEBDRIVER = "chromedriver";
    private static final String WINDOWS_IE_WEBDRIVER = "MicrosoftWebDriver";

    private static final String LINUX_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriverLinux";
    private static final String UNIX_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriver";
    private static final String WINDOWS_FIREFOX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriver.exe";

    private static final String UNIX_CHROME_WEBDRIVER_PATH = "src/main/resources/drivers/chromedriver";
    private static final String WINDOWS_CHROME_WEBDRIVER_PATH = "src/main/resources/drivers/chromedriver.exe";

    private static final String WINDOWS_IE_WEBDRIVER_PATH = "src/main/resources/drivers/MicrosoftWebDriver.exe";




    private static volatile WebDriver driver;

    public static void initialization() {

        //Firefox options
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile(FIREFOX_PROFILE_NAME);
        ffProfile.setAcceptUntrustedCertificates(true);
        ffProfile.setAssumeUntrustedCertificateIssuer(false);

        //Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);


        Properties properties = new Properties();
        try {
            InputStream inputStream = DriverInitializer.class.getResourceAsStream("/driver.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String browserType = properties.getProperty("browserType");
        String driverType = properties.getProperty("driverType");
        String driverPath = properties.getProperty("driverPath");


        System.out.println(browserType);


        switch (browserType) {
            case "firefox":
                System.setProperty(FIREFOX_WEBDRIVER, WINDOWS_FIREFOX_WEBDRIVER_PATH);
                driver = new FirefoxDriver(ffProfile);
                break;

            case "chrome":
                System.setProperty(CHROME_WEBDRIVER, WINDOWS_CHROME_WEBDRIVER_PATH);
                driver = new ChromeDriver(capabilities);
                break;

            case "firefoxLinux":
                System.setProperty(FIREFOX_WEBDRIVER, LINUX_FIREFOX_WEBDRIVER_PATH);
                DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setCapability(FirefoxDriver.PROFILE,ffProfile);
                driver = new FirefoxDriver(desiredCapabilities);
                break;

            case "firefoxMacOS":
               // System.setProperty(FIREFOX_WEBDRIVER, UNIX_FIREFOX_WEBDRIVER_PATH);
                driver = new FirefoxDriver(ffProfile);
                break;

            case "chromeLinux":
                System.setProperty(CHROME_WEBDRIVER, UNIX_CHROME_WEBDRIVER_PATH);
                driver = new ChromeDriver(capabilities);
                break;

            case "chromeMacOS":
                System.setProperty(driverType, driverPath);
                driver = new ChromeDriver(capabilities);
                break;

            case "internetExplorer":
                System.setProperty(WINDOWS_IE_WEBDRIVER, WINDOWS_IE_WEBDRIVER_PATH);
                driver = new InternetExplorerDriver();
                break;

            default:
                System.out.println(browserType + " is invalid");
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public static void setImpicityWait(Long l){
        driver.manage().timeouts().implicitlyWait(l, TimeUnit.SECONDS);
    }

    public static void resetImplicityWait(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static synchronized WebDriver instance() {
        if (driver == null) {
            initialization();
            return driver;
            }
        return driver;
    }

    public static void getToUrl(String url){
        instance().get(url);
    }

    public static void close() {
        if (driver!=null) {
            driver.quit();
            driver=null;
        }else {
            System.out.println("Cant close session");
        }
    }

}