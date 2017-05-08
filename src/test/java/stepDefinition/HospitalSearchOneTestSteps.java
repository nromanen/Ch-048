package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.allUsers.HospitalSearchResultPage;
import pages.headers.BaseHeader;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Created by ybalatc on 5/8/2017.
 */
public class HospitalSearchOneTestSteps {
    private static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";
    HospitalSearchResultPage hospitalSearchResult6;

    @Given("^I opened Base Url$")
    public void iOpenedBaseUrl() {
        DriverInitializer.getToUrl(BASE_URL);
    }

//    @When("^I try to search hospital by '555'$")
//    public void iTryToSearchHospitalByPolik() throws Throwable {
//        BaseHeader notAuthorizedHeader = new NotAuthorizedHeader();
//        hospitalSearchResult6 = notAuthorizedHeader.findHospital("555");
//        Thread.sleep(1000);
//    }
//    @When("^I try to search hospital by (\\d+)$")
//    public void iTryToSearchHospitalBy(int arg0) throws Throwable {
//        BaseHeader notAuthorizedHeader = new NotAuthorizedHeader();
//        hospitalSearchResult6 = notAuthorizedHeader.findHospital(String.valueOf(arg0));
//        Thread.sleep(1000);
//    }
    @When("^I try to search hospital by 'polik'$")
    public void iTryToSearchHospitalByPolik() throws Throwable {
        BaseHeader notAuthorizedHeader = new NotAuthorizedHeader();
        hospitalSearchResult6 = notAuthorizedHeader.findHospital("polik");
        Thread.sleep(1000);
}

    @Then("^I should see (\\d+) hospital which name, description or address consist search word$")
    public void iShouldSeeHospitalWhichNameDescriptionOrAddressConsistSearchWord(int count) throws NullPointerException {
        assertEquals(hospitalSearchResult6.countOfHospital(), count);
    }



}
