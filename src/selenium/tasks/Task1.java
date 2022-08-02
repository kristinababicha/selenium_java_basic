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
        WebElement textBox = driver.findElement(By.id("numb"));
        WebElement submitKey = driver.findElement(By.xpath("//Button[text()='Submit']"));
        WebElement errorMsg = driver.findElement(By.xpath("//p[@id='ch1_error']"));
        textBox.sendKeys("Shiva");
        submitKey.click();
        assertEquals("Please enter a number", errorMsg.getText());
    }



    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement textBox = driver.findElement(By.id("numb"));
        WebElement submitKey = driver.findElement(By.xpath("//Button[text()='Submit']"));
        WebElement errorMsg = driver.findElement(By.xpath("//p[@id='ch1_error']"));
        textBox.sendKeys("40");
        submitKey.click();
        assertEquals("Number is too small",errorMsg.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement textBox = driver.findElement(By.id("numb"));
        WebElement submitKey = driver.findElement(By.xpath("//Button[text()='Submit']"));
        WebElement errorMsg = driver.findElement(By.xpath("//p[@id='ch1_error']"));
        textBox.sendKeys("110");
        submitKey.click();
        assertEquals("Number is too big",errorMsg.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        WebElement textBox = driver.findElement(By.id("numb"));
        WebElement submitKey = driver.findElement(By.xpath("//Button[text()='Submit']"));
        textBox.sendKeys("55");
        submitKey.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 55 is 7.42",alert.getText());
        alert.accept();
    }
}
