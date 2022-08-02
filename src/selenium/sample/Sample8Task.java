package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Sample8Task {
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
        driver.get("https://kristinek.github.io/site/examples/po");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void styleChecks() throws Exception {
//         TODO:
        WebElement header = driver.findElement(By.className("w3-pale-red"));
        WebElement header2 = driver.findElement(By.className("w3-pale-yellow"));

//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);
        //rgba(255, 255, 204, 1);

        assertEquals("rgba(255, 221, 221, 1)", header.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 204, 1)", header2.getCssValue("background-color"));

//        check h1 element font-size 64px
        WebElement header3 = driver.findElement(By.tagName("h1"));
        assertEquals("64px", header3.getCssValue("font-size"));
    }
}
