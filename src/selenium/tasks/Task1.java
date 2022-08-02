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

import static org.junit.Assert.assertEquals;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        // System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
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
//        enter a text instead of a number, check that correct error is seen
        String keyText = "gizem";
        WebElement element = driver.findElement(By.id("numb"));
        element.sendKeys(keyText);


        WebElement clickButton = driver.findElement(By.xpath("//button[@type='button']"));
        clickButton.click();

        String checkKey = driver.findElement(By.className("error")).getText();

        assertEquals("Please enter a number", checkKey);
        driver.findElement(By.id("numb")).clear();

    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen

        String keyNum = "30";
        WebElement elementNum = driver.findElement(By.id("numb"));
        elementNum.sendKeys(keyNum);


        WebElement clickButton = driver.findElement(By.xpath("//button[@type='button']"));
        clickButton.click();

        String checkKeyNum = driver.findElement(By.id("ch1_error")).getText();

        assertEquals("Number is too small", checkKeyNum);
        driver.findElement(By.id("numb")).clear();

    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen

        String keyBigNum = "150";

        WebElement elementBigNum = driver.findElement(By.id("numb"));

        elementBigNum.sendKeys(keyBigNum);

        WebElement clickButton = driver.findElement(By.xpath("//button[@type='button']"));
        clickButton.click();

        String checkKeyBigNum = driver.findElement(By.id("ch1_error")).getText();

        assertEquals("Number is too big", checkKeyBigNum);
        driver.findElement(By.id("numb")).clear();


    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code

        String correctNum = "81";

        WebElement correctNumber = driver.findElement(By.id("numb"));

        correctNumber.sendKeys(correctNum);

        WebElement clickButton = driver.findElement(By.xpath("//button[@type='button']"));
        clickButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals("Square root of 81 is 9.00", alertText);
        alert.accept();
    }
}
