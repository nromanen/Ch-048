package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.admin.AllUsersPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;


/**
 * Created by Yana on 07.04.2017.
 */
public class TestHospitalSearch extends BaseTest {

    public static final String TOO_SHORT_SEARCH_WORD = "ho";

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalNotAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        HospitalSearchResultPage hospitalSearchResult = notAuthorizedHeader.findHospital(searchWord);
        assertEquals(hospitalSearchResult.countOfHospital(), expected);
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalAuthorizedUser(String searchWord, int expected) throws Exception {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        BaseNavigation.login(ADMIN_LOGIN, ADMIN_PASSWORD);
        HospitalSearchResultPage hospitalSearchResult = notAuthorizedHeader.findHospital(searchWord);
        assertEquals(hospitalSearchResult.countOfHospital(), expected);
        BaseNavigation.logout();
    }

    @Test(groups = "InputValidation")
    public void testFindHospitalInputValidationUa() throws Exception {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        notAuthorizedHeader.changeLanguageToUa();
        notAuthorizedHeader.fillHospitalInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(notAuthorizedHeader);
        assertEquals(notAuthorizedHeader.getHospitalSearchError().getText(),
                     properties.getProperty("search.validation.line.too.short")
                    );
    }

    @Test(groups = "InputValidation")
    public void testFindHospitalInputValidationEng() throws Exception {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        notAuthorizedHeader.changeLanguageToEn();
        notAuthorizedHeader.fillHospitalInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(notAuthorizedHeader);
        assertEquals(notAuthorizedHeader.getHospitalSearchError().getText(),
                     properties.getProperty("search.validation.line.too.short")
                    );
    }

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"polik", 1},
                {"hosp", 3},
                {"qwerty", 0}
        };
    }
}
