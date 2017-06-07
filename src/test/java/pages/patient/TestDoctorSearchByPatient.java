package pages.patient;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.anonymous.DoctorSearchResultPage;
import pages.allUsers.HospitalSeekerHomePage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Class describes tests for testing of doctor's search by user Patient from project's home page
 *
 * @author ybalatc
 */
public class TestDoctorSearchByPatient extends BaseTest {

    HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();

    /**
     * Method is used for testing of doctor's search with DataProvider (name "SearchProvider").
     * First it do login method using patient's login and password. Then it fills the doctor's search field with
     * search word on a home page and compare expected result with actual count of doctors that have been found on
     * a page result. If results aren't equals there will be message about it. And it does logout.
     *
     * PATIENT_LOGIN is patient's login for testing
     * PATIENT_PASSWORD is patient's password for testing
     * @param searchWord is DataProvider's search word of doctors
     * @param expected is DataProvider's expected count of doctors that have been found
     */
    @Test(dataProvider = "SearchProvider", groups = {"PatientSearchTest"})
    public void testFindDoctorAuthorizedUser(String searchWord, int expected) {
        BaseNavigation.login(PATIENT_LOGIN, PATIENT_PASSWORD);
        DoctorSearchResultPage doctorSearchResult = hospitalSeekerHomePage.header.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected,
                "Actual count of doctors that have been found isn't as expected");
        BaseNavigation.logout();
    }

    /**
     * Method is used for using few value of search word and expected result of search
     *
     * @return object with 2 parameters: searchWord and expected result
     * */
    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"hous", 1},
                {"hou", 1},
                {"qwerty", 0}
        };
    }
}
