package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
    public void errorOnText() throws Exception {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        driver.findElement(By.id("numb")).click();
        driver.findElement(By.id("numb")).sendKeys("vitavitavitavitavita");
        Thread.sleep(1000);
        driver.findElement(By.className("w3-btn")).click();
        Thread.sleep(1000);
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() throws Exception {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("12");
        Thread.sleep(1000);
        driver.findElement(By.className("w3-btn")).click();
        Thread.sleep(1000);
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() throws Exception {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("111");
        Thread.sleep(1000);
        driver.findElement(By.className("w3-btn")).click();
        Thread.sleep(1000);
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
        //and check that no error is seen and that square root is calculated correctly
        //NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        int validInputNumber = 93;
        String number = Integer.toString(validInputNumber);
        driver.findElement(By.id("numb")).sendKeys(number);
        driver.findElement(By.className("w3-btn")).click();
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        DecimalFormat df = new DecimalFormat("0.00");
        String getSqrt = df.format(Math.sqrt(validInputNumber));
//        String getSqrt = String.valueOf(Math.sqrt(validInputNumber)).substring(0, 4);
        boolean errorMsg = driver.findElement(By.id("ch1_error")).isDisplayed();
        assertEquals("Square root of " + number + " is " + getSqrt, alertText);
        assertFalse(errorMsg);
    }
}
