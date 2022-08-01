package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Sample3Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
//         check how many element with class "test" there are on page (5)
        assertEquals(5,driver.findElements(By.className("test")).size());
//         check that value of second button is "This is also a button"
        assertEquals("This is also a button", driver.findElement(By.id("ButtonId")).getAttribute("value"));

    }
    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
        //driver.findElement(By.id("ButtonId")).getAttribute("value").equalsIgnoreCase("This is Also a Button");
        assertTrue("Some custom error message", driver.findElement(By.id("ButtonId")).getAttribute("value").equalsIgnoreCase("This is also a button"));
        //("Some custom error message", driver.findElement(By.id("ButtonId")).getAttribute("value").equalsIgnoreCase("This is also a button"));
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
    }



    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String valueofButton = driver.findElement(By.id("ButtonId")).getAttribute("value");
       // assertFalse(valueofButton.equals("This is also a button")); Test if it fails
        assertFalse(valueofButton.equals("This is a button"));

    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
        List<WebElement> elems = driver.findElements(By.className("test"));
        elems.forEach(webElement -> {
            if (webElement.getText().contains("190")) {
                fail();
            }
        });
    }
}
