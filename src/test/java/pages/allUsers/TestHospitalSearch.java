package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.anonymous.HospitalSearchResultPage;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;


/**
 * Created by Yana on 07.04.2017.
 */
public class TestHospitalSearch extends BaseTest {

    public static final String TOO_SHORT_SEARCH_WORD = "ho";
    HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalNotAuthorizedUser(String searchWord, int expected) throws Exception {
        HospitalSearchResultPage hospitalSearchResult = hospitalSeekerHomePage.notAuthorizedHeader.findHospital(searchWord);
        assertEquals(hospitalSearchResult.countOfHospital(), expected,
                "Actual count of finded hospital's isn't as expected");
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationEng() throws Exception {
        hospitalSeekerHomePage.notAuthorizedHeader.changeLanguageToEn();
        hospitalSeekerHomePage.notAuthorizedHeader.fillDoctorInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.notAuthorizedHeader);
        assertEquals(hospitalSeekerHomePage.notAuthorizedHeader.getDoctorSearchError().getText(),
                properties.getProperty("search.validation.line.too.short"),
                "Search word too short. Please enter at least 3 letters"
        );
    }

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationUa() throws Exception {
        hospitalSeekerHomePage.notAuthorizedHeader.changeLanguageToUa();
        hospitalSeekerHomePage.notAuthorizedHeader.fillDoctorInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.notAuthorizedHeader);
        assertEquals(hospitalSeekerHomePage.notAuthorizedHeader.getDoctorSearchError().getText(),
                properties.getProperty("search.validation.line.too.short"),
                "Search word too short. Please enter at least 3 letters"
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