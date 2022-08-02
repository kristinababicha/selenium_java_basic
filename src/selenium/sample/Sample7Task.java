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
//        check that none of the checkboxes are ticked
        WebElement Checkbox1= driver.findElement(By.id("vfb-6-0"));
        WebElement Checkbox2= driver.findElement(By.id("vfb-6-1"));
        WebElement Checkbox3= driver.findElement(By.id("vfb-6-2"));

        assertFalse(Checkbox1.isSelected());
        assertFalse(Checkbox2.isSelected());
        assertFalse(Checkbox3.isSelected());

//        tick  "Option 2"
        Checkbox2.click();

//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(Checkbox1.isSelected());
        assertFalse(Checkbox3.isSelected());
        assertTrue(Checkbox2.isSelected());
//        tick  "Option 3"
        Checkbox3.click();
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement resultCheckBox= driver.findElement(By.id("result_checkbox"));
        assertEquals("You selected value(s): Option 2, Option 3",resultCheckBox.getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        WebElement radioButton1= driver.findElement(By.id("vfb-7-1"));
        WebElement radioButton2= driver.findElement(By.id("vfb-7-2"));
        WebElement radioButton3= driver.findElement(By.id("vfb-7-3"));

        List<WebElement> radioButtonsList= driver.findElements(By.xpath("//input[@type='radio']"));

        for(int i = 1; i < radioButtonsList.size(); i++) {
            assertFalse(radioButtonsList.get(i).isSelected());
        }

      //  assertFalse(radioButton1.isSelected());
        //assertFalse(radioButton2.isSelected());
        //assertFalse(radioButton3.isSelected());


//        select  "Option 3"
        radioButton3.click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radioButton1.isSelected());
        assertFalse(radioButton2.isSelected());
        assertTrue(radioButton3.isSelected());

//        select  "Option 1"
        radioButton1.click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(radioButton2.isSelected());
        assertFalse(radioButton3.isSelected());
        assertTrue(radioButton1.isSelected());
//        click result
        WebElement Result= driver.findElement(By.id("result_button_ratio"));
        Result.click();
//        check that 'You selected option: Option 1' text is being displayed
        WebElement resultradio = driver.findElement(By.id("result_radio"));
        assertEquals("You selected option: Option 1",resultradio.getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByVisibleText("Option 3");
//        check that selected option is "Option 3"
       assertEquals("Option 3",dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals(1,dropdown.getAllSelectedOptions().size());
        assertEquals("Option 2",dropdown.getAllSelectedOptions().get(0).getText());
//        click result
        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2",driver.findElement(By.id("result_select")).getText());
    }


}
