package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.headers.BaseHeader;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;


/**
 * Created by Yana on 07.04.2017.
 */
public class TestHospitalSearch extends BaseTest {

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
//                {"поліклініка", 2},
                {"polik", 1},
                {"hosp", 3},
                {"qwerty", 0}
        };
    }

    @BeforeMethod
    public void beforeMethod() {
        DriverInitializer.getToUrl(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception{
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalNotAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        HospitalSearchResultPage hospitalSearchResult = header.findHospital(searchWord);
        Thread.sleep(1000);
        assertEquals(hospitalSearchResult.countOfHospital(), expected);
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader header = new NotAuthorizedHeader();
        BaseNavigation.login("admin@hospitals.ua", "1111");
        Thread.sleep(1000);
        HospitalSearchResultPage hospitalSearchResult = header.findHospital(searchWord);
        assertEquals(hospitalSearchResult.countOfHospital(), expected);
    }

    @Test(groups = "InputValidation")
    public void testFindHospitalInputValidationUa() throws Exception {
        BaseHeader header = new BaseHeader();
        header.changeLanguageToUa();
        Thread.sleep(1000);
        header.fillHospitalInput("ho");
        BaseTest.checkLanguageAndLoadProperties(header);
        assertEquals(header.getHospitalSearchError().getText(), properties.getProperty("lineToShort"));
    }

    @Test(groups = "InputValidation")
    public void testFindHospitalInputValidationEng() throws Exception {
        BaseHeader header = new BaseHeader();
        header.changeLanguageToEn();
        Thread.sleep(1000);
        header.fillHospitalInput("ho");
        BaseTest.checkLanguageAndLoadProperties(header);
        assertEquals(header.getHospitalSearchError().getText(), properties.getProperty("lineToShort"));
    }
}
