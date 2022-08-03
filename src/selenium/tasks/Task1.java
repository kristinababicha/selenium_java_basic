package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.*;

import java.io.File;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() throws InterruptedException {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("Kristina");

        driver.findElement(By.className("w3-btn")).click();
        Thread.sleep(5000);
//
        assertEquals("Please enter a number", driver.findElement(By.className("error")).getText());


    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        // input 44
        driver.findElement(By.id("numb")).sendKeys("44");
        driver.findElement(By.className("w3-btn")).click();

        assertEquals("Number is too small", driver.findElement(By.className("error")).getText());


    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("103");
        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Number is too big", driver.findElement(By.className("error")).getText());


    }

    @Test
    public void correctSquareRoot() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code

        // Driver inputs and accepts number in the input field
        int inputNumber = 64;
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(inputNumber));
        driver.findElement(By.className("w3-btn")).click();

        // Driver switches to alert
        Alert alert = driver.switchTo().alert();
        // Driver gets the text of an alert
        System.out.println(alert.getText());

        // We calculate square root result by ourselves
        double squareRoot = Math.sqrt(inputNumber);
        System.out.println(String.format("%,.2f", squareRoot));

        // We create expected message, that consists of 1. input number 2. calculated square root
        // Calculated square root is converted to string, because we need to format number to have 2 digits after comma
        String expectedAlertStatement = "Square root of " + inputNumber + " is " + String.format("%,.2f", squareRoot);
        System.out.println(expectedAlertStatement);

        // We assert expected and the actual alert messages
        assertEquals(expectedAlertStatement, alert.getText());
        alert.accept();

        // We assert that there is no any visible error messages
        String errorMessage = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("", errorMessage);

    }
}
