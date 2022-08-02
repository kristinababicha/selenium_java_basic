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
        WebElement input = driver.findElement(By.id("number"));
        WebElement resBtn = driver.findElement(By.id("result_button_number"));
        WebElement clearBtn = driver.findElement(By.id("clear_result_button_number"));
        WebElement result_number = driver.findElement(By.id("result_number"));

// NOT FOR BEST PRACTICE. MIGHT BE IGNORED!!!
//        if(!clearBtn.isEnabled() && !result_number.isEnabled()){
//            //my code
//        }else{
//            System.out.println("Smt wrong");
//        }

        input.clear(); //without it. the result will append


        input.sendKeys("7");
        assertFalse(clearBtn.isEnabled());
        assertFalse(result_number.isDisplayed());
//
        assertTrue(resBtn.isEnabled());
//        Thread.sleep(3000);
        resBtn.click();

        assertTrue(clearBtn.isEnabled());
        assertTrue(result_number.isDisplayed());
        assertTrue(resBtn.isEnabled());

        assertEquals("7", input.getAttribute("value"));

        clearBtn.click();
//        Thread.sleep(3000);
        assertFalse(clearBtn.isEnabled());
        assertFalse(result_number.isDisplayed());
        assertTrue(resBtn.isEnabled());

//        check that button is not clickable "Clear Result"
//        check that text is not displayed
//        click on "Result" button
//        check that text is displayed
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//        check that the button "Clear Result" is clickable now
//        click on "Clear Result"
//        check that the text is now (""), but it is not displayed
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
