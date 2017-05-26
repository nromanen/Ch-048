package pages.allUsers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.NotAuthorizedHeader;

/**
 * Created by Evgen on 05.04.2017.
 */
public class HospitalSeekerHomePage implements PageInitializer {

    public NotAuthorizedHeader notAuthorizedHeader;

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
        notAuthorizedHeader = new NotAuthorizedHeader();
        pageInitialization();
    }
}