package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

        driver = new ChromeDriver(dc);
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement errorMessageElem;
        WebElement inputField = driver.findElement(By.cssSelector("#numb"));
        inputField.sendKeys("Test txt");
        driver.findElement(By.cssSelector("button")).click();
        errorMessageElem = driver.findElement(By.cssSelector("#ch1_error"));
        assertTrue(errorMessageElem.isDisplayed());
        assertEquals("Please enter a number",errorMessageElem.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement errorMessageElem;
        WebElement inputField = driver.findElement(By.cssSelector("#numb"));
        inputField.sendKeys("48");
        driver.findElement(By.cssSelector("button")).click();
        errorMessageElem = driver.findElement(By.cssSelector("#ch1_error"));
        assertTrue(errorMessageElem.isDisplayed());
        assertEquals("Number is too small",errorMessageElem.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement errorMessageElem;
        WebElement inputField = driver.findElement(By.cssSelector("#numb"));
        inputField.sendKeys("128");
        driver.findElement(By.cssSelector("button")).click();
        errorMessageElem = driver.findElement(By.cssSelector("#ch1_error"));
        assertTrue(errorMessageElem.isDisplayed());
        assertEquals("Number is too big",errorMessageElem.getText());
    }

    @Test
    public void correctSquareRoot() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code

        DecimalFormat df = new DecimalFormat("0.00");
        int testValue = 64;
        double sqrRoot = Math.sqrt(testValue);

        String expResultTxt = String.format("Square root of %s is %s", testValue, df.format(sqrRoot));
        WebElement inputField = driver.findElement(By.cssSelector("#numb"));
        inputField.sendKeys(String.valueOf(testValue));

        driver.findElement(By.cssSelector("button")).click();

        Alert alert = driver.switchTo().alert();
        assertEquals(expResultTxt, driver.switchTo().alert().getText());
        alert.accept();
        Thread.sleep(2000);
    }
}
