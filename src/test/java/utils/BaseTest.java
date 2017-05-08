package utils;

import org.testng.annotations.*;
import pages.headers.BaseHeader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


@Listeners({ScreenshotListener.class})
public class BaseTest {

    public static Properties properties = null;

    public static final String ADMIN_LOGIN = "admin@hospitals.ua";
    public static final String ADMIN_PASSWORD = "1111";

    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";

    public static final String DOCTOR_LOGIN = "doctor.cb@hospitals.ua";
    public static final String DOCTOR_PASSWORD = "1111";

    public static final String PATIENT_LOGIN = "patient.cd@hospitals.ua";
    public static final String PATIENT_PASSWORD = "1111";

    protected static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";

    @BeforeClass(alwaysRun = true)
    public void before() {
        DriverInitializer.getToUrl(BASE_URL);
    }


    @AfterClass(alwaysRun = true)
    public void after() {
        DriverInitializer.close();
    }


    public static void checkLanguageAndLoadProperties(BaseHeader header) {
        properties = new Properties();
        try {
            if (header.getChangeLanguageIco().getAttribute("src").endsWith("/en.png")) {
                properties.load(new InputStreamReader(
                        new FileInputStream("src/main/resources/languageEng.properties"), "UTF-8"));
            } else {
                properties.load(new InputStreamReader(
                        new FileInputStream("src/main/resources/languageUa.properties"), "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}