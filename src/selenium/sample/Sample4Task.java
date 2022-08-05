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
        String number = "56";
        WebElement numberField=driver.findElement(By.id("number"));
        numberField.clear();
        numberField.sendKeys(number);


//        check that button is not clickable "Clear Result"
        WebElement clearResultButton = driver.findElement(By.xpath("//Button[@id='clear_result_button_number']"));
        assertFalse(clearResultButton.isEnabled());

        //        check that text is not displayed
        WebElement checkResultText = driver.findElement(By.id("result_number"));
        assertFalse(checkResultText.isDisplayed());

//        click on "Result" button
        WebElement resultButton = driver.findElement(By.xpath("//Button[@id='result_button_number']"));
        resultButton.click();

//        check that text is displayed
        assertTrue(checkResultText.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        String text = "You entered number: \"" + number + "\"";
        assertEquals(text, checkResultText.getText());
//        check that the button "Clear Result" is clickable now
        assertTrue(clearResultButton.isEnabled());
//        click on "Clear Result"
        clearResultButton.click();
//        check that the text is now (""), but it is not displayed
        assertEquals("", numberField.getText());

    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
//        check that current url is not base_url
        assertNotEquals(base_url, driver.getCurrentUrl());
//        verify that current url is homepage
        assertEquals("https://kristinek.github.io/site/", driver.getCurrentUrl());
    }
}
