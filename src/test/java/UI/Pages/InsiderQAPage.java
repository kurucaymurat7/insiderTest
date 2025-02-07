package UI.Pages;

import core.Asserts.Asserts;
import core.Utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static core.Commands.Commands.*;

public class InsiderQAPage {
    public InsiderQAPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[.='See all QA jobs']")
    public WebElement buttonSeeAllQAJobs;

    @FindBy(id = "select2-filter-by-department-container")
    public WebElement dropDownFilterByDepartment;

    public void clickSeeAllQAJobs() {
        click(buttonSeeAllQAJobs);
    }

    @FindBy(xpath = "//*[@id='jobs-list']//*[@class='position-list-item-wrapper bg-light']//p")
    public List<WebElement> listPositions;

    @FindBy(xpath = "//*[@id='jobs-list']//*[@class='position-list-item-wrapper bg-light']//span")
    public List<WebElement> listDepartments;

    @FindBy(xpath = "//*[@id='jobs-list']//*[@class='position-list-item-wrapper bg-light']//div")
    public List<WebElement> listLocations;

    @FindBy(id = "select2-filter-by-location-container")
    public WebElement dropDownFilterByLocation;

    @FindBy(xpath = "//*[@class='select2-results']//li")
    public List<WebElement> dropDownListLocations;

    @FindBy(xpath = "(//*[@class='select2-results']//li)[16]")
    public WebElement dropDownQualityAssurance;

    @FindBy(xpath = "(//*[@class='btn btn-yellow rounded has-icon page-button prev pl-4'])[1]")
    public WebElement buttonLeftArrow;

    @FindBy(xpath = "//*[@id='jobs-list']//*[@class='position-list-item-wrapper bg-light']")
    public List<WebElement> blocksJobsListed;

    public void filterByQualiyAssurance() {
        click(dropDownFilterByDepartment);
        click(dropDownQualityAssurance);
        clickKey(Keys.ARROW_DOWN, 10);
    }

    public void filterbyIstanbulTurkey() {
        click(dropDownFilterByLocation);
        click(dropDownListLocations.get(1));
    }

    @FindBy(xpath = "(//a[.='View Role'])[1]")
    public WebElement buttonViewRole;

    public void waitUntilDepartmentShowsQualityAssurance() {
        String departmentShown;
        while (true) {
            departmentShown = dropDownFilterByDepartment.getAttribute("title");
            System.out.println("departmentShown = " + departmentShown);
            if (departmentShown.equals("Quality Assurance")) {
                break;
            } else
                waitfor(1);
        }
    }

    public int verifyJobslisted() {
        int sizeJobsListed = blocksJobsListed.size();
        System.out.println("sizeJobsListed = " + sizeJobsListed);
        Asserts.assertTrue(sizeJobsListed > 0);
        return sizeJobsListed;
    }

    public void verifyPositionContains(int size, String keyword1, String keyword2) {
        String position;
        for (int i = 0; i < size; i++) {
            position = listPositions.get(i).getText();
            System.out.println("position = " + position);
            try {
                Asserts.assertTrue(position.contains(keyword1) | position.contains(keyword2));
            } catch (AssertionError e) {
                refreshPage();
                waitUntilDepartmentShowsQualityAssurance();
                filterbyIstanbulTurkey();
                waitfor(2);
                position = listPositions.get(i).getText();
                System.out.println("position = " + position);
                Asserts.assertTrue(position.contains(keyword1) | position.contains(keyword2));
            }
        }
    }

    public void verifyDepartmentContains(int size, String keyword) {
        for (int i = 0; i < size; i++) {
            String department = listDepartments.get(i).getText();
            System.out.println("department = " + department);
            Asserts.assertTrue(department.contains(keyword));
        }
    }

    public void verifyLocationContains(int size, String keyword) {
        for (int i = 0; i < size; i++) {
            String location = listLocations.get(i).getText();
            System.out.println("location = " + location);
            Asserts.assertTrue(location.contains(keyword));
        }
    }

    public void clickViewRole() {
        String currentWindowHandle = Driver.getDriver().getWindowHandle();
        try {
            hover(buttonViewRole);
        } catch (Exception e) {
            waitfor(1);
            hover(buttonViewRole);
        }
        click(buttonViewRole);
        swithtoWindowNewlyOpened(currentWindowHandle);
    }

    public void scrollDown(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", buttonViewRole);
    }
}
