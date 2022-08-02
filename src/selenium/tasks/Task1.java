package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Locale;

import static java.lang.Math.sqrt;
import static org.junit.Assert.*;

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
    public void errorOnText() {
//        TODO
//         enter a text instead of a number, check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("qwerty");
        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("45");
        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//         enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("105");
        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is seen and that square root is calculated correctly
//         NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        String square = "64";
        double double_square = Double.parseDouble(square);
        double root = Math.sqrt(double_square);
        driver.findElement(By.id("numb")).sendKeys(square);
        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Square root of " + square + " is " + String.format(Locale.ROOT, "%.2f", root), driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }
}
