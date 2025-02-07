package core.Asserts;

import core.Commands.Commands;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static core.Commands.Commands.getScreenshot;

public class Asserts {
    public static void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
        System.out.println("ASSERTION PASSED : CONDITION : " + condition);
    }

    public static void assertEquals(int variable1, int variable2) {
        System.out.println("variable1 = " + variable1);
        System.out.println("variable2 = " + variable2);
        try {
            Assert.assertEquals(variable1, variable2);
            System.out.println("ASSERTION PASSED");
        } catch (AssertionError e) {
            Assert.fail();
        }
    }

    public static void assertEquals(String variable1, String variable2) {
        System.out.println("variable1 = " + variable1);
        System.out.println("variable2 = " + variable2);
        try {
            Assert.assertEquals(variable1, variable2);
            System.out.println("ASSERTION PASSED");
        } catch (AssertionError e) {
            System.out.println("ASSERTION PASSED");
            Assert.fail();
        }
    }

    public static void assertIsDisplayed(WebElement webElement) {
        try {
            Assert.assertTrue(webElement.isDisplayed());
            System.out.println("webElement = " + webElement.getText() + " is displayed");
        } catch (Exception e) {
            System.out.println("webElement = " + webElement.getText() + " is NOT displayed");
            Assert.fail();
        }
    }
}
