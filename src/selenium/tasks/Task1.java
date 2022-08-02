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
    public void errorOnText() throws InterruptedException {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        driver.findElement(By.id("numb")).click();
        String text = "Text";
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.sendKeys(text);
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("Please enter a number",driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void errorOnNumberTooSmall() throws InterruptedException {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        driver.findElement(By.id("numb")).click();
        String text = "37";
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.sendKeys(text);
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void errorOnNumberTooBig() throws InterruptedException {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).click();
        String text = "127";
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.sendKeys(text);
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void correctSquareRoot() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        driver.findElement(By.id("numb")).click();
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.sendKeys("77");
        driver.findElement(By.className("w3-orange")).click();
        Alert alert = driver.switchTo().alert();

        int number = 77;
        double result = Math.sqrt(number);
        String res = String.format("%.2f", result);
        System.out.println("The square root of " + number + " is " + result);

        assertEquals("Square root of 77 is " + res, alert.getText());
        alert.accept();
        Thread.sleep(3000);
        }
    }
