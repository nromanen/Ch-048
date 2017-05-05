package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.allUsers.HospitalSearchResultPage;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.BrowserWrapper;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Created by ybalatc on 5/4/2017.
 */

public class AnonymousHospitalSearchSteps {
    private NotAuthorizedHeader header;
    private HospitalSearchResultPage hospitalSearchResult;

    @When("^I try to search hospital by <search_word>$")
    public void iTryToSearchHospitalBySearch_word() {
        header = new NotAuthorizedHeader();
        hospitalSearchResult = header.findHospital("polik");
    }

    @Then("^I should see <expected_number> hospitals which name, description or address consist search word$")
    public void iShouldSeeExpected_numberHospitalsWhichNameDescriptionOrAddressConsistSearchWord() {
        assertEquals(hospitalSearchResult.countOfHospital(), 7);
    }


//    @When("^I try to search hospital by <search_word>$")
//    public void iTryToSearchHospitalBySearch_word(String search_word) {
//        header = new NotAuthorizedHeader();
//        hospitalSearchResult = header.findHospital(search_word);
//    }
//
//    @Then("^I should see <expected_number> hospitals which name, description or address consist search word$")
//    public void iShouldSeeExpected_numberHospitalsWhichNameDescriptionOrAddressConsistSearchWord(int expected_number) {
//        assertEquals(hospitalSearchResult.countOfHospital(), expected_number);
//    }
}
