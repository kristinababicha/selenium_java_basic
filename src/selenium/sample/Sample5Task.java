package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author vigneshkrishnan
 */
public class Sample5Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get("https://kristinek.github.io/site/examples/alerts_popups");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        WebElement alertPageViaButton = driver.findElement(By.xpath("//button[text() = 'To go to alerted page press Ok. Or stay here']"));
        alertPageViaButton.click();

//        switch to alert
//        click ok
        Alert al = driver.switchTo().alert();
        al.accept();

//        switch to second alert
//        verify alert text
        assertEquals("Booooooooo!",driver.switchTo().alert().getText());

        //click ok on second alert
        Alert al1 = driver.switchTo().alert();
        // click on OK to accept with accept()
        al1.accept();
       // driver.switchTo().alert().accept();
//        verify that the correct page is opened
        assertEquals("https://kristinek.github.io/site/examples/alerted_page",driver.getCurrentUrl());
    }
    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.xpath("//button[text()='To go to alerted page press Ok. Or stay here']")).click();
//        switch to alert
//        click cancel
        driver.switchTo().alert().dismiss();

//        verify the text on page
        String text = "So you desided to say? Good! ";
        assertEquals("text",driver.findElement(By.id("textForAlerts")).getText());

    }
}
