package core.Commands;

import core.Utilities.ConfigurationReader;
import core.Utilities.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Set;

public class Commands {

    public static int defaultWait = 1;
    public static void click(WebElement webElement) {
        try {
            waitfor(defaultWait);
            webElement.click();
        } catch (Exception e) {
            waitfor(defaultWait);
            webElement.click();
        }
    }

    public static void doubleClick(WebElement webElement) {
        Actions actions = new Actions(Driver.getDriver());
        try {
            actions.doubleClick(webElement).build().perform();
        } catch (Exception e) {
            waitfor(defaultWait);
            actions.doubleClick(webElement).build().perform();
        }
    }

    public static void waitfor(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void navigateToURL(String url) {
        Driver.getDriver().navigate().to(url);
    }

    public static String getAttributeforWebElement(WebElement webElement, String attribute) {
        String currentValue = null;
        try {
            if (webElement.isDisplayed()) {
                currentValue = webElement.getAttribute(attribute);
            }
        } catch (Exception e) {
            waitfor(defaultWait);
            currentValue = webElement.getAttribute(attribute);
        }
        return currentValue;
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).build().perform();
    }

    public static void clickKey(Keys key, int N) {
        Actions actions = new Actions(Driver.getDriver());
        for (int i = 0; i < N; i++) {
            actions.sendKeys(key).build().perform();
        }
    }

    public static void getScreenshot(){
        try {
            File screenshot_with_scenario_name = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            String pathToScreenshotsFolder = KnownPaths.getResultScreenShotsFilePath("");
            String timeStamp = getCurrentDateTime();
            FileUtils.copyFile(screenshot_with_scenario_name, new File(pathToScreenshotsFolder + timeStamp + "_result.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCurrentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatterSaat = DateTimeFormatter.ofPattern("YYYYMMddHHmm");
        return formatterSaat.format(dateTime);
    }

    public static void deleteFilesInFolder(String pathToFolder) {
        File f = new File(pathToFolder);

        String[] files;
        try {
            files = f.list();
            for (String file : files) {
                File currentFile = new File(f.getPath(), file);
                System.out.println("file deleted. " + currentFile.delete());
            }
        } catch (Exception ignored) {
            System.out.println("file not deleted");
        }
    }

    public static void refreshPage() {
        Driver.getDriver().navigate().refresh();
        waitfor(defaultWait);
    }

    public static void swithtoWindowNewlyOpened(String currentWindowHandle) {
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();

        for (String each : allWindowHandles) {
            if (!currentWindowHandle.equals(each)) {
                Driver.getDriver().switchTo().window(each);
            }
        }
    }

    public void scrollDown(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
