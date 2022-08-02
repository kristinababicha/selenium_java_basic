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
//         TODO:
//        check that none of the checkboxes are ticked (is selected()), assertFalse (is selected for), better to check as a list
//       List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3"));
//       for (WebElement checkBox : checkBoxes) {
//           assertFalse("Null", checkBox.isSelected());
//       }
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
            checkBox.click();

        }

        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        WebElement option2= driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        option2.click();
        option3.click();
//        assertTrue(option3.isSelected());
        driver.findElement(By.cssSelector("#result_button_checkbox")).click();
        Thread.sleep(5000);


//        tick  "Option 2" , tick option 2

//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked, AssertTrue or assertFalse
//        tick  "Option 3"
//        click result
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed, AssertEquals
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected , is selected (), assertFalse.
        List<WebElement> elems = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        for (WebElement elem : elems) {
            assertFalse(elem.isSelected());
        }
//        select  "Option 3",
        elems.get(2).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected assertfalse and assert ture
        assertFalse(elems.get(0).isSelected());
        assertFalse(elems.get(1).isSelected());
        assertTrue(elems.get(2).isSelected());
//        select  "Option 1"
        elems.get(0).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected , again assertions
        assertFalse(elems.get(1).isSelected());
        assertFalse(elems.get(2).isSelected());
        assertTrue(elems.get(0).isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed. isDisplayed
        assertEquals ("You selected option: Option 1",driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select, first find element Dropdown. Than we define element, with class select.(single line)
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
//        check that selected option is "Option 3" (select by Index, or by value)
        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals(1, dropdown.getAllSelectedOptions().size());
        assertEquals("Option 2", dropdown.getAllSelectedOptions().get(0).getText());
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
        //select a specific date in date picker,
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
    }
}
