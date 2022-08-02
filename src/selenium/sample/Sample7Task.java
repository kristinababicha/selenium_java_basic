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
import java.sql.Driver;
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
//          check that none of the checkboxes are ticked
//          tick  "Option 2"
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//          tick  "Option 3"
//          click result
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }

        driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox'")).click();
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox'")).isSelected());
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox'")).isSelected());
        driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox'")).click();
        driver.findElement(By.id("result_button_checkbox")).click();
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//          check that none of the radio are selected
//          select  "Option 3"
//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//          select  "Option 1"
//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//          click result
//          check that 'You selected option: Option 1' text is being displayed
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }

        driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio'")).click();
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio'")).isSelected());
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio'")).isSelected());
        assertTrue(driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio'")).isSelected());
        driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio'")).click();
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio'")).isSelected());
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio'")).isSelected());
        assertTrue(driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio'")).isSelected());

        driver.findElement(By.id("result_button_ratio")).click();
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        TODO:
//         select "Option 3" in Select
//         check that selected option is "Option 3"
//         select "Option 2" in Select
//         check that selected option is "Option 2"
//         click result
//         check that 'You selected option: Option 2' text is being displayed
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
        driver.findElement(By.id("result_button_select")).click();
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
//          check that correct date is added
        driver.findElement(By.id("vfb-8")).click();
        String target_date = "July 2007";
        String current_date = driver.findElement(By.className("ui-datepicker-month")).getText() + " " + driver.findElement(By.className("ui-datepicker-year")).getText();
        while (!current_date.equals(target_date)) {
            driver.findElement(By.className("ui-icon-circle-triangle-w")).click();
            current_date = driver.findElement(By.className("ui-datepicker-month")).getText() + " " + driver.findElement(By.className("ui-datepicker-year")).getText();
        }
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[4]/a")).click();
        driver.findElement(By.id("result_button_date")).click();
        assertEquals("You entered date: 07/04/2007", driver.findElement(By.id("result_date")).getText());
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
        driver.findElement(By.id("vfb-8")).click();
        driver.findElement(By.className("ui-state-default")).click();
        driver.findElement(By.id("vfb-8")).clear();
        driver.findElement(By.id("vfb-8")).sendKeys("05/02/1959");
        driver.findElement(By.id("result_button_date")).click();
        assertEquals("You entered date: 05/02/1959", driver.findElement(By.id("result_date")).getText());
    }
}
