package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
    public void errorOnText() {
//        TODO
//        enter a text instead of a number,
        WebElement numberArea = driver.findElement(By.id("numb"));
        numberArea.clear();
        numberArea.sendKeys("test");
        driver.findElement(By.cssSelector(".w3-margin[type='button']")).click();
//        check that correct error is seen
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50),
        WebElement numberArea = driver.findElement(By.id("numb"));
        numberArea.clear();
        numberArea.sendKeys("35");
        driver.findElement(By.cssSelector(".w3-margin[type='button']")).click();
//        check that correct error is seen
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100),
        WebElement numberArea = driver.findElement(By.id("numb"));
        numberArea.clear();
        numberArea.sendKeys("125");
        driver.findElement(By.cssSelector(".w3-margin[type='button']")).click();
//        check that correct error is seen
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
        String number = "81";

        WebElement numberArea = driver.findElement(By.id("numb"));
        numberArea.clear();
        numberArea.sendKeys(number);

        driver.findElement(By.cssSelector(".w3-margin[type='button']")).click();

//        and check that no error is seen
//        and that square root is calculated correctly

        DecimalFormat num = new DecimalFormat("0.00");
        String mathResult = num.format(Math.sqrt(Double.parseDouble(number)));
        mathResult = mathResult.replace(',', '.');

        String expectedText = "Square root of " + number + " is " + mathResult;
        assertEquals(expectedText, driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();

        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
    }
}
