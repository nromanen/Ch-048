package testSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.allUsers.HospitalSearchResult;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;



/**
 * Created by Yana on 07.04.2017.
 */
public class testHospitalSearch {
    private WebDriver driver;
//    private String baseUrl;
//    private boolean acceptNextAlert = true;
//    private StringBuffer verificationErrors = new StringBuffer();
//    String searchButton = "a[href*='#toggle-search']";
//    String selectSearch = "select_search";
//    String selectFind = "button.btn-info";
//    String urlHome = "HospitalSeeker/";
//    String urlSearch = "hospitals?q=";
//    String classOfCss = "[class='card panel panel-default text-xs-right']";
    int expected;
    String searchWord;
    HospitalSearchResult hospitalSearchResult = new HospitalSearchResult(driver);

    //@Parameters
    @DataProvider(name = "SearchProvider")
    //  public static List<Object[]> parametrizedData() {
    public static Object[][] parametrizedData() {
        return new Object[][]{
                // return Arrays.asList(new Object[][] {
                { "поліклініка", 1 },
                { "Angel", 2 },
                { "абрвал", 0 },
        };
    }


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver   = new FirefoxDriver();
        HospitalSearchResult.baseUrl = "https://localhost:8443/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(dataProvider="SearchProvider")
    public void testSeleniumTestFindHospital(String searchWord, int expected) throws Exception {
        HospitalSearchResult.isElementPresent(hospitalSearchResult);

        //need import of BaseSearch Class
        assertEquals(expected, HospitalSearchResult.testMain(searchWord));
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.close();
        }
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }



}
