package pages.allUsers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.NotAuthorizedHeader;

/**
 * Created by Evgen on 05.04.2017.
 */
public class HospitalSeekerHomePage implements PageInitializer {

    public NotAuthorizedHeader header;

    @FindBy(xpath = "//*[@id=\"carouselHacked\"]/div[2]/div[3]/div/h1")
    private WebElement textHeader;

    @FindBy(xpath = "//*[@id=\"carouselHacked\"]/div[2]/div[2]/div/p")
    private WebElement textParagraph;

    @FindBy(css = "a.left.carousel-control")
    private WebElement leftCarouselButton;

    @FindBy(css = "a.right.carousel-control")
    private WebElement rightCarouselButton;

    @FindBy(css = "img[src=\"/HospitalSeeker/img/slide-one.jpg\"]")
    protected WebElement homePageImage;

    public WebElement getHomePageImage() {
        return homePageImage;
    }

    public HospitalSeekerHomePage() {
        header = new NotAuthorizedHeader();
        pageInitialization();
    }

    public HospitalSeekerHomePage changeLanguageToEn() {
        header.changeLanguageIco.click();
        header.enLanguage.click();
        return new HospitalSeekerHomePage();
    }

    public HospitalSeekerHomePage changeLanguageToUa() {
        header.changeLanguageIco.click();
        header.uaLanguage.click();
        return new HospitalSeekerHomePage();
    }

}