package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Created by Yana on 17.04.2017.
 */
public class TestDoctorSearch extends BaseTest {

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"hous", 1},
                {"hou", 1},
                {"qwerty", 0}
        };
    }

    @BeforeMethod
    public void beforeMethod() {
        DriverInitializer.getToUrl(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorNotAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        DoctorSearchResultPage doctorSearchResult = notAuthorizedHeader.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        BaseNavigation.login("admin@hospitals.ua", "1111");
        Thread.sleep(1000);
        DoctorSearchResultPage doctorSearchResult = notAuthorizedHeader.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationEng() throws Exception {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        notAuthorizedHeader.changeLanguageToUa();
        Thread.sleep(1000);
        notAuthorizedHeader.fillDoctorInput("ho");
        BaseTest.checkLanguageAndLoadProperties(notAuthorizedHeader);
        assertEquals(notAuthorizedHeader.getDoctorSearchError().getText(), properties.getProperty("lineToShort"));
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationUa() throws Exception {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        notAuthorizedHeader.changeLanguageToEn();
        Thread.sleep(1000);
        notAuthorizedHeader.fillDoctorInput("ho");
        BaseTest.checkLanguageAndLoadProperties(notAuthorizedHeader);
        assertEquals(notAuthorizedHeader.getDoctorSearchError().getText(), properties.getProperty("lineToShort"));
    }
}