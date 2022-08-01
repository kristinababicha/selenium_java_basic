package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number"
//        WebElement textArea = driver.findElement(By.id("number"));
//        textArea.clear();
//        textArea.sendKeys("25");
        String inputNumber = "25";
        driver.findElement(By.id("number")).clear();
        driver.findElement(By.id("number")).sendKeys(inputNumber);
//      check that button is not clickable "Clear Result"
        assertFalse(driver.findElement(By.id("clear_result_button_number")).isEnabled());
//        WebElement showButton = driver.findElement(By.id("clear_result_button_number"));
//        assertFalse(showButton.isEnabled());
//        check that text is not displayed
        assertFalse(driver.findElement(By.id("result_number")).isDisplayed());
//        click on "Result" button
        driver.findElement(By.id("result_button_number")).click();
//        check that text is displayed
        assertTrue(driver.findElement(By.id("result_number")).isDisplayed());
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
 //       assertEquals("You entered number: \"" + textArea +"\"",
        assertEquals("You entered number: \"" + inputNumber +"\"",
        driver.findElement(By.id("result_number")).getText());
//        check that the button "Clear Result" is clickable now
        assertTrue(driver.findElement(By.id("clear_result_button_number")).isEnabled());
//        click on "Clear Result"
        driver.findElement(By.id("clear_result_button_number")).click();
//        check that the text is now (""), but it is not displayed
        assertEquals("", driver.findElement(By.id("result_number")).getText());
        assertFalse(driver.findElement(By.id("result_number")).isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        assertEquals(base_url, driver.getCurrentUrl());
        driver.findElement(By.id("homepage_link")).click();
//      assertEquals("Link Page 1", driver.findElement(By.id("h1")).getText());
        assertNotEquals(base_url, driver.getCurrentUrl());
        assertEquals("https://kristinek.github.io/site/", driver.getCurrentUrl());
    }
}
