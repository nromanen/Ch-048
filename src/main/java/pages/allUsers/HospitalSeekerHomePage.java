package pages.allUsers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.anonymous.LoginPage;
import pages.headers.headersByRole.DoctorHeader;
import pages.headers.headersByRole.NotAuthorizedHeader;


/**
 * Created by Evgen on 05.04.2017.
 */
public class HospitalSeekerHomePage implements PageInitializer {

    //Temporary field
    public NotAuthorizedHeader notAuthorizedHeader;
   // public DoctorHeader header;


    public LoginPage moveToLoginPage(){
        notAuthorizedHeader.loginButton();
        return new LoginPage();
    }



    //End of temporary
    @FindBy(xpath = "//*[@id=\"carouselHacked\"]/div[2]/div[3]/div/h1")
    private WebElement textHeader;

    @FindBy(xpath = "//*[@id=\"carouselHacked\"]/div[2]/div[2]/div/p")
    private WebElement textParagraph;


    @FindBy(css = "left carousel-control")
    private WebElement leftCarouselButton;

    @FindBy(css = "right carousel-control")
    private WebElement rightCarouselButton;

    @FindBy(css = "img[src=\"/HospitalSeeker/img/slide-four.jpg\"]")
    private WebElement image;


    public HospitalSeekerHomePage() {
        notAuthorizedHeader = new NotAuthorizedHeader();
        pageInitialization();
    }

}
