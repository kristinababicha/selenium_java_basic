package selenium.tasks;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.util.List;

import static java.lang.Math.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

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
    public void errorOnText() throws Exception  {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        String inputText = "Silence";
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(inputText);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(5000);
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Please enter a number",
                driver.findElement(By.id("ch1_error")).getText());
    }
    private String roundSQrt(String b){
        double in = Double.parseDouble(b);
        double inq = Math.round(Math.sqrt(in) * 100d) / 100d;
        return String.format("%.2f",inq);
    }
    @Test
    public void errorOnNumberTooSmall() throws Exception {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        String inputSmall = "30";
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(inputSmall);
        driver.findElement(By.xpath("//div[2]/button")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Number is too small",
                driver.findElement(By.id("ch1_error")).getText());

        driver.navigate().refresh();
        String inputBugSmall = "49";
        String displayedBugText = "Square root of " + inputBugSmall + " is " + roundSQrt(inputBugSmall);
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(inputBugSmall);
        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(5000);
        Alert result = driver.switchTo().alert();
        String keksis = result.getText();
        assertEquals(displayedBugText, keksis);
        result.accept();
        Thread.sleep(5000);
        System.out.println("Number 49 made alert not error message");

    }
    @Test
    public void errorOnNumberTooBig() throws Exception {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        String inputGross = "101";
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(inputGross);
        driver.findElement(By.xpath("//div[2]/button")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Number is too big",
                driver.findElement(By.id("ch1_error")).getText());

        String inputBugGross = "666";
        String displayedBugText = "Square root of " + inputBugGross + " is " + roundSQrt(inputBugGross);
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(inputBugGross);
        driver.findElement(By.xpath("//div[2]/button")).click();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        System.out.println("Number 666 do not display errors message");
    }
    @Test
    public void correctSquareRoot()  throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        String inputNormal = "51";
        String displayedText = "Square root of " + inputNormal + " is " + roundSQrt(inputNormal);
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(inputNormal);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(5000);
        Alert result = driver.switchTo().alert();
        String keksis = result.getText();
        assertEquals(displayedText, keksis);
        result.accept();
        Thread.sleep(5000);
    }

    @Test
    public void correctSquareRoot2()  throws Exception {
            String inputNormal = "99";
            String displayedText = "Square root of " + inputNormal + " is " + roundSQrt(inputNormal);
            driver.findElement(By.id("numb")).clear();
            driver.findElement(By.id("numb")).sendKeys(inputNormal);
            Thread.sleep(5000);
            driver.findElement(By.xpath("//div[2]/button")).click();
            Thread.sleep(5000);
            Alert result = driver.switchTo().alert();
            String keksis = result.getText();
            assertEquals(displayedText, keksis);
            result.accept();
            Thread.sleep(5000);
    }
}
