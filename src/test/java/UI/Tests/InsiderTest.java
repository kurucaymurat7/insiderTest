package UI.Tests;

import UI.Pages.InsiderApplicationFormPage;
import UI.Pages.InsiderHomePage;
import UI.Pages.InsiderQAPage;
import core.Utilities.ConfigurationReader;
import core.Utilities.Driver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import static core.Commands.Commands.*;

public class InsiderTest {

    @BeforeMethod
    public void deleteScreenShotsFolder(ITestResult result) {
        String description = result.getMethod().getDescription();
        System.out.println("description = " + description);
    }

    @Test(invocationCount = 5, description = "Insider Page UI Test")
    public void testInsider() {
        navigateToURL(ConfigurationReader.getProperty("insiderHomePage")); // open insider home page

        InsiderHomePage insiderPage = new InsiderHomePage();
        insiderPage.verifyInsiderPageOpened();

        insiderPage.acceptCookies();

        insiderPage.clickCompany();
        insiderPage.clickCareers();

        insiderPage.verifyLocations();
        insiderPage.verifySeeAllTeams();
        insiderPage.verifyLifeAtInsider();

        navigateToURL(ConfigurationReader.getProperty("insiderQAPage"));

        InsiderQAPage insiderQAPage = new InsiderQAPage();
        insiderQAPage.clickSeeAllQAJobs();

        insiderQAPage.waitUntilDepartmentShowsQualityAssurance();
        insiderQAPage.filterbyIstanbulTurkey();
        insiderQAPage.filterByQualiyAssurance();

        int sizeJobsListed = insiderQAPage.verifyJobslisted();
        insiderQAPage.verifyPositionContains(sizeJobsListed, "Quality Assurance", "QA");
        insiderQAPage.verifyDepartmentContains(sizeJobsListed, "Quality Assurance");
        insiderQAPage.verifyLocationContains(sizeJobsListed, "Istanbul, Turkiye");

        insiderQAPage.clickViewRole();

        InsiderApplicationFormPage insiderApplicationFormPage = new InsiderApplicationFormPage();
        insiderApplicationFormPage.verifyApplicationFormPageOpened();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE)
            getScreenshot();

        Driver.closeDriver();
    }
}
