package stepDefinition;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.allUsers.HospitalSearchResultPage;

import static org.testng.Assert.assertEquals;

/**
 * Created by ybalatc on 5/8/2017.
 */
public class HospitalSearchSteps {

    HospitalSearchResultPage hospitalSearchResult = new HospitalSearchResultPage();

    @When("^I try to search hospital by (.*)$")
    public void iTryToSearchHospitalBySearch_word(String search_word) {
        hospitalSearchResult = hospitalSearchResult.notAuthorizedHeader.findHospital(search_word);
    }

    @Then("^I should see (\\d+) hospitals which name, description or address consist search word$")
    public void iShouldSeeExpected_numberHospitalsWhichNameDescriptionOrAddressConsistSearchWord(int expectedNumber) {
        assertEquals(hospitalSearchResult.countOfHospital(), expectedNumber);
    }
}
