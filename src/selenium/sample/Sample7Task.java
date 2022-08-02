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
import java.util.Calendar;
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
//         TODO:
//        check that none of the checkboxes are ticked
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement cb : checkboxes) {
            assertFalse(cb.isSelected());
        }
//        tick  "Option 2"
        checkboxes.get(1).click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(checkboxes.get(0).isSelected());
        assertFalse(checkboxes.get(2).isSelected());
        assertTrue(checkboxes.get(1).isSelected());
//        tick  "Option 3"
        checkboxes.get(2).click();
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        String text = "You selected value(s): Option 2, Option 3";
        assertEquals(text, driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        List<WebElement> radio = driver.findElements(By.xpath("//*[@type='radio']"));
        for (WebElement rb : radio) {
            assertFalse(rb.isSelected());
        }
//        select  "Option 3"
        radio.get(2).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radio.get(0).isSelected());
        assertFalse(radio.get(1).isSelected());
        assertTrue(radio.get(2).isSelected());
//        select  "Option 1"
        driver.findElement(By.id("vfb-7-1")).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(radio.get(1).isSelected());
        assertFalse(radio.get(2).isSelected());
        assertTrue(radio.get(0).isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        String text = "You selected option: Option 1";
        assertEquals(text, driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
//        select "Option 3" in Select
        dropdown.selectByVisibleText("Option 3");
//        check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
        assertEquals(1, dropdown.getAllSelectedOptions().size());
        assertEquals("Option 2", dropdown.getAllSelectedOptions().get(0).getText());
//        click result
        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        String text = "You selected option: Option 2";
        assertEquals(text, driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
        WebElement dateArea = driver.findElement(By.id("vfb-8"));
        dateArea.clear();
        dateArea.click();
        WebElement dataWidget = driver.findElement(By.id("vfb-8"));
        for (int i = 0; i < 181; i++) {
            dataWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
        dataWidget.findElement(By.xpath("//a[text()='4']")).click();
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        assertEquals("07/04/2007", dateArea.getAttribute("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
        String date = "05/02/1959";
        WebElement dateArea = driver.findElement(By.id("vfb-8"));
        dateArea.clear();
        dateArea.sendKeys(date);
//        check that correct date is added
        assertEquals(date, dateArea.getAttribute("value"));
    }
}
