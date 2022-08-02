package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
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
        WebElement resultBtn = driver.findElement(By.cssSelector("#result_button_checkbox"));
        WebElement resultTxt = driver.findElement(By.cssSelector("#result_checkbox"));
        WebElement checkbox1 = driver.findElement(By.cssSelector("input[value='Option 1']"));
        WebElement checkbox2 = driver.findElement(By.cssSelector("input[value='Option 2']"));
        WebElement checkbox3 = driver.findElement(By.cssSelector("input[value='Option 3']"));
        System.out.println("The checkbox1 is selection state is - " + checkbox1.isSelected());
        System.out.println("The checkbox2 is selection state is - " + checkbox2.isSelected());
        System.out.println("The checkbox3 is selection state is - " + checkbox3.isSelected());
//        tick  "Option 2"
        checkbox2.click();
        Thread.sleep(2000);
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(checkbox1.isSelected());
        assertFalse(checkbox3.isSelected());
        assertTrue(checkbox2.isSelected());
//        tick  "Option 3"
        checkbox3.click();
        Thread.sleep(2000);
//        click result
        resultBtn.click();
        Thread.sleep(2000);
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3", resultTxt.getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        WebElement radio1 = driver.findElement(By.cssSelector("#vfb-7-1"));
        WebElement radio2 = driver.findElement(By.cssSelector("#vfb-7-2"));
        WebElement radio3 = driver.findElement(By.cssSelector("#vfb-7-3"));
        WebElement btnResult = driver.findElement(By.cssSelector("#result_button_ratio"));
        WebElement resultTxt = driver.findElement(By.cssSelector("#result_radio"));

//        System.out.println("The radio1 is selection state is - " + radio1.isSelected());
//        System.out.println("The radio2 is selection state is - " + radio2.isSelected());
//        System.out.println("The radio3 is selection state is - " + radio3.isSelected());
        assertFalse(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertFalse(radio3.isSelected());
//        select  "Option 3"
        radio3.click();
        Thread.sleep(2000);
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertTrue(radio3.isSelected());
//        select  "Option 1"
        radio1.click();
        Thread.sleep(2000);
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertFalse(radio3.isSelected());
//        click result
        btnResult.click();
        Thread.sleep(2000);
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1",resultTxt.getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select select = new Select(driver.findElement(By.cssSelector("#vfb-12")));
        WebElement rbtn = driver.findElement(By.cssSelector("#result_button_select"));
        WebElement txtDisplayed = driver.findElement(By.cssSelector("#result_select"));
//        select "Option 3" in Select
        select.selectByValue("value3");
//        check that selected option is "Option 3"
        assertEquals("Option 3",select.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        select.selectByValue("value2");
//        check that selected option is "Option 2"
        assertEquals("Option 2",select.getFirstSelectedOption().getText());
//        click result
        rbtn.click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2", txtDisplayed.getText());
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
        WebElement dateField = driver.findElement(By.cssSelector("#vfb-8"));
        WebElement resBtn = driver.findElement(By.cssSelector("#result_button_date"));
        WebElement resTxt = driver.findElement(By.cssSelector("#result_date"));
        dateField.sendKeys("02/05/1959");
        driver.findElement(By.cssSelector("body")).click();
        Thread.sleep(1000);
        resBtn.click();
        Thread.sleep(1000);
        assertEquals("You entered date: 02/05/1959",resTxt.getText());
    }
}
