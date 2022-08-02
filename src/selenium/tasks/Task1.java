package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

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
        WebElement NumberEntry = driver.findElement(By.id("numb"));
        NumberEntry.sendKeys(" No ");
        Thread.sleep(100);

        WebElement EntryButton = driver.findElement(By.className("w3-orange"));
        EntryButton.click();
        Thread.sleep(100);

        WebElement CheckText = driver.findElement(By.className("error"));
        assertEquals("Please enter a number" , "Please enter a number");
    }

    @Test
    public void errorOnNumberTooSmall() throws InterruptedException {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement NumberEntry = driver.findElement(By.id("numb"));
        NumberEntry.sendKeys(" 48 ");
        Thread.sleep(100);

        WebElement EntryButton = driver.findElement(By.className("w3-orange"));
        EntryButton.click();
        Thread.sleep(100);

        WebElement CheckText = driver.findElement(By.className("error"));
        assertEquals("Number is too small" , "Number is too small");

    }

    @Test
    public void errorOnNumberTooBig() throws InterruptedException {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement NumberEntry = driver.findElement(By.id("numb"));
        NumberEntry.sendKeys(" 111 ");
        Thread.sleep(100);

        WebElement EntryButton = driver.findElement(By.className("w3-orange"));
        EntryButton.click();
        Thread.sleep(100);

        WebElement CheckText = driver.findElement(By.className("error"));
        assertEquals("Number is too big" , "Number is too big");
        Thread.sleep(100);

    }


    @Test
    public void correctSquareRoot() throws InterruptedException {
        final DecimalFormat decimalformat = new DecimalFormat("0.00");
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        WebElement NumberEntry = driver.findElement(By.id("numb"));
        NumberEntry.sendKeys(" 80 ");
        Thread.sleep(1000);

        WebElement EntryButton = driver.findElement(By.className("w3-orange"));
        EntryButton.click();
        Thread.sleep(1000);

        String res = decimalformat.format(Math.sqrt(80));
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of  80  is " + res, alert.getText()); //If no 2 spaces in front and after (number) not equal
        Thread.sleep(1000);

        alert.accept();
        }
    }


