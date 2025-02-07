package core.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        String browser = ConfigurationReader.getProperty("browser");

        if (driver == null) {
            if (browser.equals("chrome")) {
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
            } else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static void closeDriver() throws IOException {
        if (driver != null) {//if the driver is pointing chrome
            //close all windows
            Set<String> handles = driver.getWindowHandles();
            System.out.println("Open chromes size = " + handles.size());
            for (String handle : handles) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver = null;//set it back to null to make sure driver is null
    }

    public static void refreshDriver() {
        driver.navigate().refresh();
    }

}