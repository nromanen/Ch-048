package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.doctor.ListPatientaPage;
import pages.doctor.WorkSchedulerPage;
import utils.BrowserWrapper;

/**
 * Created by Evgen on 06.04.2017.
 */
public class DoctorHeader extends AuthorizedHeader implements PageInitializer {

    public DoctorHeader() {
        pageInitialization();
    }

    @FindBy(css = "a[href=\"/HospitalSeeker/patients\"]")
    private WebElement patients;

    @FindBy(css = "a[href=\"/HospitalSeeker/workscheduler\"]")
    private WebElement schedule;


    public ListPatientaPage patientsButtonClick(){
        BrowserWrapper.waitUntilElementClickable(patients);
        patients.click();
        return new ListPatientaPage();
    }
    public WorkSchedulerPage scheduleButtonClick(){
        schedule.click();
        return new WorkSchedulerPage();
    }

}
