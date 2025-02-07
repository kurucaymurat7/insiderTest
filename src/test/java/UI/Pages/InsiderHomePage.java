package UI.Pages;

import core.Asserts.Asserts;
import core.Utilities.ConfigurationReader;
import core.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.Commands.Commands.*;

public class InsiderHomePage {
    public InsiderHomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "wt-cli-accept-all-btn")
    public WebElement buttonAcceptCookies;

    @FindBy(xpath = "(//a[@id='navbarDropdownMenuLink'])[5]")
    public WebElement headerCompany;

    @FindBy(xpath = "//div[@class='new-menu-dropdown-layout-6-mid-container']//a[2]")
    public WebElement headerCareers;

    @FindBy(xpath = "//h3[@class='category-title-media ml-0']")
    public WebElement blockLocations;

    @FindBy(xpath = "//a[@class='btn btn-outline-secondary rounded text-medium mt-5 mx-auto py-3 loadmore']")
    public WebElement blockSeeAllTeams;

    @FindBy(xpath = "(//h2[@class='elementor-heading-title elementor-size-default'])[2]")
    public WebElement blockLifeAtInsider;

    public void acceptCookies() {
        click(buttonAcceptCookies);
    }

    public void clickCompany() {
        click(headerCompany);
    }

    public void clickCareers() {
        click(headerCareers);
    }

    public void verifyLocations() {
        Asserts.assertIsDisplayed(blockLocations);
    }

    public void verifySeeAllTeams() {
        Asserts.assertIsDisplayed(blockSeeAllTeams);
    }

    public void verifyLifeAtInsider() {
        Asserts.assertIsDisplayed(blockLifeAtInsider);
    }

    public void verifyInsiderPageOpened() {
        String currentURL = Driver.getDriver().getCurrentUrl();
        Asserts.assertEquals(ConfigurationReader.getProperty("insiderHomePage"), currentURL);
    }
}
