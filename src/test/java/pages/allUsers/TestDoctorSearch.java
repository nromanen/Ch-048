package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.anonymous.DoctorSearchResultPage;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Created by Yana on 17.04.2017.
 */
public class TestDoctorSearch extends BaseTest {

    public static final String TOO_SHORT_SEARCH_WORD = "ho";
    HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorNotAuthorizedUser(String searchWord, int expected) throws Exception {
        DoctorSearchResultPage doctorSearchResult = hospitalSeekerHomePage.notAuthorizedHeader.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationEng() throws Exception {
        hospitalSeekerHomePage.notAuthorizedHeader.changeLanguageToEn();
        hospitalSeekerHomePage.notAuthorizedHeader.fillDoctorInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.notAuthorizedHeader);
        assertEquals(hospitalSeekerHomePage.notAuthorizedHeader.getDoctorSearchError().getText(),
                properties.getProperty("search.validation.line.too.short")
        );
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationUa() throws Exception {
        hospitalSeekerHomePage.notAuthorizedHeader.changeLanguageToUa();
        hospitalSeekerHomePage.notAuthorizedHeader.fillDoctorInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.notAuthorizedHeader);
        assertEquals(hospitalSeekerHomePage.notAuthorizedHeader.getDoctorSearchError().getText(),
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