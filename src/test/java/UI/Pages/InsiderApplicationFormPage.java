package UI.Pages;

import core.Asserts.Asserts;
import core.Utilities.ConfigurationReader;
import core.Utilities.Driver;
import org.openqa.selenium.support.PageFactory;

import static core.Commands.Commands.waitfor;

public class InsiderApplicationFormPage {
    public InsiderApplicationFormPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void verifyApplicationFormPageOpened() {
        String currentURL = Driver.getDriver().getCurrentUrl();
        for (int i = 0; i < 3; i++) {
            try {
                Asserts.assertEquals(ConfigurationReader.getProperty("insiderApplicationFormPage"), currentURL);
                break;
            } catch (AssertionError e) {
                waitfor(1);
            }
        }

    }
}
