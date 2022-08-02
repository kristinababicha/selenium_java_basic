package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);

//        WebElement s1 = driver.findElement(By.className("//.w3-light-grey w3-padding w3-margin-bottom w3-center"));
//        WebElement s2 = driver.findElement(By.xpath("//h1"));
//        assertEquals("block", s1.getCssValue("display"));
//        assertEquals("rgba(255, 221, 221, 1)",s1.getCssValue("background-color"));
          assertEquals("rgba(255, 221, 221, 1)",
                  driver.findElement(By.className("w3-pale-red")).getCssValue("background-color"));
          assertEquals("rgba(255, 255, 204, 1)",
                driver.findElement(By.className("w3-pale-yellow")).getCssValue("background-color"));
//        assertEquals("block", s2.getCssValue("display"));
//        assertEquals("rgba(255, 255, 221,)",s2.getCssValue("background-color"));


//        check h1 element font-size 64px
        WebElement hdg1 = driver.findElement(By.tagName("h1"));
        assertEquals("64px", hdg1.getCssValue("font-size"));


    }
}
