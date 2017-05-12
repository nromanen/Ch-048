package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Created by Yana on 17.04.2017.
 */
public class TestDoctorSearch extends BaseTest {

    public static final String TOO_SHORT_SEARCH_WORD = "ho";

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorNotAuthorizedUser(String searchWord, int expected) throws Exception {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        DoctorSearchResultPage doctorSearchResult = hospitalSeekerHomePage.header.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorAuthorizedUser(String searchWord, int expected) throws Exception {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        BaseNavigation.login(ADMIN_LOGIN, ADMIN_PASSWORD);
        DoctorSearchResultPage doctorSearchResult = hospitalSeekerHomePage.header.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
        BaseNavigation.logout();
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationEng() throws Exception {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        hospitalSeekerHomePage.changeLanguageToEn();
        hospitalSeekerHomePage.header.fillDoctorInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.header);
        assertEquals(hospitalSeekerHomePage.header.getDoctorSearchError().getText(),
                     properties.getProperty("search.validation.line.too.short")
                    );
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationUa() throws Exception {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        hospitalSeekerHomePage.changeLanguageToUa();
        hospitalSeekerHomePage.header.fillDoctorInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.header);
        assertEquals(hospitalSeekerHomePage.header.getDoctorSearchError().getText(),
                     properties.getProperty("search.validation.line.too.short")
                    );
    }

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"hous", 1},
                {"hou", 1},
                {"qwerty", 0}
        };
    }
}