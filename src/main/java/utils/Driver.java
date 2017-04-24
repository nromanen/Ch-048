package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
public class Driver {

    private static final String FIREFOX_PROFILE_NAME = "default";

    private static final String FIREFOX_WEBDRIVER = "webdriver.gecko.driver";
    private static final String CHROME_WEBDRIVER = "chromedriver";
    private static final String WINDOWS_IE_WEBDRIVER = "MicrosoftWebDriver";


    private static final String UNIX_FIREFOX_WEBDRIVER_PATH = "src/main/resources/geckodriver";
    private static final String WINDOWS_FIREFOX_WEBDRIVER_PATH = "src/main/resources/geckodriver.exe";

    private static final String UNIX_CHROME_WEBDRIVER_PATH = "src/main/resources/chromedriver";
    private static final String WINDOWS_CHROME_WEBDRIVER_PATH = "src/main/resources/chromedriver.exe";

    private static final String WINDOWS_IE_WEBDRIVER_PATH = "src/main/resources/MicrosoftWebDriver.exe";


    private static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";

    private static WebDriver driver;

    public static void initialization() {
        System.out.println("Step 1");

        //Firefox options
         ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile(FIREFOX_PROFILE_NAME);
        ffProfile.setAcceptUntrustedCertificates(true);
        ffProfile.setAssumeUntrustedCertificateIssuer(false);

        //Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);


        Properties properties = new Properties();
        try {
            System.out.println("Step 2");
            InputStream inputStream = Driver.class.getResourceAsStream("/driver.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String browserType = properties.getProperty("browserType");
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
                System.setProperty(FIREFOX_WEBDRIVER, UNIX_FIREFOX_WEBDRIVER_PATH);
                driver = new FirefoxDriver(ffProfile);
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
               // System.setProperty(CHROME_WEBDRIVER, UNIX_CHROME_WEBDRIVER_PATH);
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




        //driver = new FirefoxDriver(ffProfile);


        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }



    public static WebDriver instance(){
        return driver;
    }

    public static void close(){
        driver.quit();
    }

}
