package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
//         TODO:
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
//        check that none of the checkboxes are ticked
        assertFalse(driver.findElement(By.id("vfb-6-0")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-2")).isSelected());
//        tick  "Option 2"
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        option2.click();
        Thread.sleep(5000);
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());
        assertFalse(option1.isSelected());
//        tick  "Option 3"
        option3.click();
        Thread.sleep(5000);
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
        Thread.sleep(5000);
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed

        assertEquals("You selected value(s): Option 2, Option 3",         driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
        List<WebElement> elems = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
//        check that none of the radio are selected
        for (WebElement element : elems) {
            assertFalse(element.isSelected());
        }
//        select  "Option 3"
        elems.get(2).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(elems.get(0).isSelected());
        assertFalse(elems.get(1).isSelected());
        assertTrue(elems.get(2).isSelected());
//        select  "Option 1"
        elems.get(0).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(elems.get(0).isSelected());
        assertFalse(elems.get(1).isSelected());
        assertFalse(elems.get(2).isSelected());
//        click result
            driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1",driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropDown = new Select(driver.findElement(By.className("w3-select")));
//        select "Option 3" in Select
        dropDown.selectByVisibleText("Option 3");
//        check that selected option is "Option 3"
        assertEquals("Option 3", dropDown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dropDown.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals(1, dropDown.getAllSelectedOptions().size());
        assertEquals("Option 2", dropDown.getAllSelectedOptions().get(0).getText());
//        click result
        driver.findElement(By.id("result_Button_select")).click();
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
//        enter date '2 of May 1959' using text    check that correct date is added
        WebElement dateInputField = driver.findElement(By.id("vfb-8"));
        dateInputField.click();
        dateInputField.sendKeys("02/05/1959");
        dateInputField.sendKeys(Keys.ENTER);
        driver.findElement(By.id("result_button_date")).click();
        assertEquals("You entered date: 02/05/1959", driver.findElement(By.id("result_date")).getText());

    }
}
