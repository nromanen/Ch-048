package pages.patient;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.PatientHeader;

/**
 * Created by gregtar on 06.04.17.
 */
public class MyAppointmentPage implements PageInitializer {
    PatientHeader patientHeader;

    @FindBy(id = "dhx_minical_icon")
    private WebElement calendarIcon;

    @FindBy(className = "dhx_cal_today_button")
    private WebElement todayButton;

    @FindBy(className = "dhx_cal_prev_button")
    private WebElement prevButton;

    @FindBy(className = "dhx_cal_next_button")
    private WebElement nextButton;

    @FindBy(className = "back-to-top")
    private WebElement backToTopButton;

    @FindBy(xpath = "//*[@id=\"scheduler_here\"]")
    private WebElement appointmentTable;

    @FindBy(className = "dhx_cal_date")
    private WebElement todayDateLabel;

    @FindBy(name = "day_tab")
    private WebElement dayTabButton;

    @FindBy(name = "week_tab")
    private WebElement weekTabButton;

    @FindBy(name = "month_tab")
    private WebElement monthTabButton;


    public MyAppointmentPage() {
        patientHeader= new PatientHeader();
        pageInitialization();

    }
}
