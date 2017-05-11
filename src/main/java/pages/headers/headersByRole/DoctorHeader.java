package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.doctor.ListPatientPage;
import pages.doctor.WorkSchedulerPage;

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


    public ListPatientPage patientsButtonClick(){
        //BrowserWrapper.waitUntilElementClickable(patients);
        patients.click();
        return new ListPatientPage();
    }
    public WorkSchedulerPage scheduleButtonClick(){
        schedule.click();
        return new WorkSchedulerPage();
    }

}
