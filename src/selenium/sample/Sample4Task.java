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
//
        Thread.sleep(1000);
        String inputNumber = "44";
        WebElement inputElem = driver.findElement(By.id("number"));
        WebElement resultDisplayElem = driver.findElement(By.id("result_number"));
        WebElement btnClearResultElem = driver.findElement(By.id("clear_result_button_number"));
        WebElement btnResultElem = driver.findElement(By.id("result_button_number"));

//        TODO:
//        enter a number under "Number"
        inputElem.clear();
        inputElem.sendKeys(inputNumber);
//        check that button is not clickable "Clear Result"
        Thread.sleep(2000);
        assertFalse(btnClearResultElem.isEnabled());
//        check that text is not displayed
        assertFalse(resultDisplayElem.isDisplayed());
//        click on "Result" button
        btnResultElem.click();
        Thread.sleep(2000);
//        check that text is displayed
        assertTrue(resultDisplayElem.isDisplayed());
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals( "You entered number: \"" + inputNumber + "\"", resultDisplayElem.getText());
//        check that the button "Clear Result" is clickable now
        assertTrue(btnClearResultElem.isEnabled());
//        click on "Clear Result"
        btnClearResultElem.click();
//        check that the text is now (""), but it is not displayed
        assertEquals("", resultDisplayElem.getText());
        assertFalse(resultDisplayElem.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
        WebElement homepageLinkElem = driver.findElement(By.id("homepage_link"));
        String homepageUrl = "https://kristinek.github.io/site/";
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        homepageLinkElem.click();
        Thread.sleep(5000);
//        check that current url is not base_url
        assertNotEquals(base_url, driver.getCurrentUrl());
//        verify that current url is homepage
        System.out.println(driver.getCurrentUrl());
        assertEquals(homepageUrl, driver.getCurrentUrl());
    }
}
