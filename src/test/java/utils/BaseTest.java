package utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import pages.headers.BaseHeader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Listeners({ScreenshotListener.class})
public class BaseTest {

    public static Properties properties;

    public static final String ADMIN_LOGIN = "admin@hospitals.ua";
    public static final String ADMIN_PASSWORD = "1111";

    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";

    public static final String DOCTOR_LOGIN = "doctor.cb@hospitals.ua";
    public static final String DOCTOR_PASSWORD = "1111";

    public static final String PATIENT_LOGIN = "patient.cd@hospitals.ua";
    public static final String PATIENT_PASSWORD = "1111";

    public static final String BASE_URL = "https://tomcat:8443/HospitalSeeker/";


    @BeforeClass(alwaysRun = true)
    public void before() {
        DriverInitializer.getToUrl(BASE_URL);
        String s = System.getProperty("test.language");
        BaseNavigation.changeLanguage(s);
    }


    @AfterClass(alwaysRun = true)
    public void after() {
        DriverInitializer.close();
    }

    public static void checkLanguageAndLoadProperties(BaseHeader header) {
        properties = new Properties();
        try {
            if (header.getChangeLanguageIco().getAttribute("src").contains("en")) {
                InputStream inputStream = new FileInputStream("src/test/resources/localization/en.properties");
                properties.load(inputStream);
            } else {
                InputStream inputStream = new FileInputStream("src/test/resources/localization/ua.properties");
                properties.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}