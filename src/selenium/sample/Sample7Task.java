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
        //driver.close();
    }

    @Test
    public void selectCheckBox() throws Exception {
        List<WebElement> checkBox = driver.findElements(By.xpath("//input[@type='checkbox']"));
//        check that none of the checkboxes are ticked
        for(WebElement check : checkBox){
            assertFalse(check.isSelected());
        }
//        tick  "Option 2"
        checkBox.get(1).click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(checkBox.get(0).isSelected());
        assertTrue(checkBox.get(1).isSelected());
        assertFalse(checkBox.get(2).isSelected());
//        tick  "Option 3"
        checkBox.get(2).click();
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
        List<WebElement> radioButton = driver.findElements(By.xpath("//input[@type='radio']"));
//        check that none of the radio are selected
        assertFalse(radioButton.get(0).isSelected());
        assertFalse(radioButton.get(1).isSelected());
        assertFalse(radioButton.get(2).isSelected());
//        select  "Option 3"
        radioButton.get(2).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radioButton.get(0).isSelected());
        assertFalse(radioButton.get(1).isSelected());
        assertTrue(radioButton.get(2).isSelected());
//        select  "Option 1"
        radioButton.get(0).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radioButton.get(0).isSelected());
        assertFalse(radioButton.get(1).isSelected());
        assertFalse(radioButton.get(2).isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());

    }

    @Test
    public void selectOption() throws Exception {
        WebElement select = driver.findElement(By.className("w3-select"));
//        select "Option 3" in Select
        Select mark = new Select(select);
        mark.selectByIndex(3);
//        check that selected option is "Option 3"
        assertEquals("Option 3", mark.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        mark.selectByIndex(2);
//        check that selected option is "Option 2"
        assertEquals(1, mark.getAllSelectedOptions().size());
        assertEquals("Option 2", mark.getAllSelectedOptions().get(0).getText());
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
