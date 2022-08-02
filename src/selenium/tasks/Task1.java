package selenium.tasks;

import javafx.beans.binding.Bindings;
import javafx.scene.input.InputMethodTextRun;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.text.DecimalFormat;
import java.lang.Math;
import org.junit.Assert;

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
        WebElement text=driver.findElement(By.id("numb"));
        Thread.sleep(2000);
        text.sendKeys("bootcamp");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
       assertEquals("Please enter a number",driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(5000);


    }

    @Test
    public void errorOnNumberTooSmall() throws InterruptedException {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement below=driver.findElement(By.id("numb"));
        below.sendKeys("45");
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        Thread.sleep(2000);
        assertEquals("Number is too small",driver.findElement(By.id("ch1_error")).getText());


    }

    @Test
    public void errorOnNumberTooBig() throws InterruptedException {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement above=driver.findElement(By.id("numb"));
        above.sendKeys("101");
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        Thread.sleep(2000);
        assertEquals("Number is too big",driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() throws InterruptedException {
//        TODO

//        enter a number between 50 and 100 digit in the input, then press submit

        String Number = "100";
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(Number);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        Thread.sleep(3000);

         double x=100;

        DecimalFormat decimalFormat = new DecimalFormat("#00.00");
       String result =decimalFormat.format(Math.sqrt(x));

        System.out.println(result);

        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 100 is "+result,alert.getText());



alert.accept();









//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
    }
}
