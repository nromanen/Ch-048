package pages.allUsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.anonymous.HospitalSearchResultPage;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Class describes tests for testing of hospital's search by not authorised user from project's home page.
 *
 * @author ybalatc
 */
public class TestHospitalSearch extends BaseTest {

    public static final String TOO_SHORT_SEARCH_WORD = "ho";
    HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();

    /**
     * Method is used for deleting all cookies after each method.
     */

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        DriverInitializer.deleteAllCookies();
    }

    /**
     * Method is used for testing of hospital's search with DataProvider (name "SearchProvider").
     * It fills the hospital's search field with search word on a home page and compare expected result with actual count
     * of hospitals that have been found on a page result. If results aren't equals there will be message about it.
     *
     * @param searchWord is DataProvider's search word of hospital
     * @param expected is DataProvider's expected count of hospitals that have been found
     */

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalNotAuthorizedUser(String searchWord, int expected) {
        HospitalSearchResultPage hospitalSearchResult = hospitalSeekerHomePage.notAuthorizedHeader.findHospital(searchWord);
        assertEquals(hospitalSearchResult.countOfHospital(), expected,
                "Actual count of hospitals that have been found isn't as expected");
    }

    /**
     * Method is used for testing of hospital's search with too short search word using English version of site.
     * First it changes language to English. Than fills the hospital's search field with search word.
     * Method uses localisation method - it checks current language and chooses error message for this language. And
     * compare this error message with message that is shown on web-site. If results aren't equals there will be message
     * about it.
     *
     * TOO_SHORT_SEARCH_WORD is search word that contains 2 letters
     */

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationEng() {
        hospitalSeekerHomePage.changeLanguageToEn();
        hospitalSeekerHomePage.notAuthorizedHeader.fillDoctorInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.notAuthorizedHeader);
        assertEquals(hospitalSeekerHomePage.notAuthorizedHeader.getDoctorSearchError().getText(),
                properties.getProperty("search.validation.line.too.short"),
                "Search word too short. Please enter at least 3 letters"
        );
    }

    /**
     * Method is used for testing of hospital's search with too short search word using Ukrainian version of site.
     * First it changes language to Ukrainian. Than fills the hospital's search field with search word.
     * Method uses localisation method - it checks current language and chooses error message for this language. And
     * compare this error message with message that is shown on web-site. If results aren't equals there will be message
     * about it.
     *
     * TOO_SHORT_SEARCH_WORD is search word that contains 2 letters
     */

    @Test(groups = "InputValidation")
    public void testFindDoctorInputValidationUa() {
        hospitalSeekerHomePage.changeLanguageToUa();
        hospitalSeekerHomePage.notAuthorizedHeader.fillDoctorInput(TOO_SHORT_SEARCH_WORD);
        BaseTest.checkLanguageAndLoadProperties(hospitalSeekerHomePage.notAuthorizedHeader);
        assertEquals(hospitalSeekerHomePage.notAuthorizedHeader.getDoctorSearchError().getText(),
                properties.getProperty("search.validation.line.too.short"),
                "Search word too short. Please enter at least 3 letters"
        );
    }

    /**
     * Method is used for using few value of search word and expected result of search
     *
     * @return object with 2 parameters: searchWord and expected result
     */

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"polik", 1},
                {"hosp", 3},
                {"qwerty", 0}
        };
    }
}