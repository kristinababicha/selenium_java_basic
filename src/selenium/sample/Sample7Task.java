package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Sample7Task {
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
    public void selectCheckBox() throws Exception {
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@class='w3-check']"));
//         TODO:
//        check that none of the checkboxes are ticked
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }
//        tick  "Option 2"
        driver.findElements(By.xpath("//input[@class='w3-check']")).get(1).click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(driver.findElements(By.xpath("//input[@class='w3-check']")).get(0).isSelected());
        assertFalse(driver.findElements(By.xpath("//input[@class='w3-check']")).get(2).isSelected());
//        tick  "Option 3"
        driver.findElements(By.xpath("//input[@class='w3-check']")).get(2).click();
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        //        check that none of the radio are selected
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }
//        select  "Option 3"
        driver.findElements(By.cssSelector(".w3-check[type='radio']")).get(2).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(driver.findElements(By.cssSelector(".w3-check[type='radio']")).get(0).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check[type='radio']")).get(1).isSelected());
        assertTrue(driver.findElements(By.cssSelector(".w3-check[type='radio']")).get(2).isSelected());
//        select  "Option 1"
        driver.findElements(By.cssSelector(".w3-check[type='radio']")).get(0).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(driver.findElements(By.cssSelector(".w3-check[type='radio']")).get(2).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check[type='radio']")).get(1).isSelected());
        assertTrue(driver.findElements(By.cssSelector(".w3-check[type='radio']")).get(0).isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dp = new Select(driver.findElement(By.id("vfb-12")));
//        select "Option 3" in Select
        dp.selectByVisibleText("Option 3");
//        check that selected option is "Option 3"
        assertEquals("Option 3", dp.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dp.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals("Option 2", dp.getAllSelectedOptions().get(0).getText()); //why get 0 index????
//        click result
        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
    }
}
