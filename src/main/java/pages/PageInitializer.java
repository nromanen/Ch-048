package pages;

import org.openqa.selenium.support.PageFactory;
import utils.DriverInitializer;

/**
 * Created by radgast on 25.04.17.
 */
public interface PageInitializer {
    default void pageInitialization(){
        PageFactory.initElements(DriverInitializer.instance(), this);
    }
}
