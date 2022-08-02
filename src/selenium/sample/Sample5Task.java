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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
// click on "To go to alerted page press Ok. Or stay here" button
        WebElement goToAlertedPageBtn= driver.findElement(By.className("w3-blue"));
        String expectedPage ="https://kristinek.github.io/site/examples/alerted_page";
        goToAlertedPageBtn.click();
        Thread.sleep(1000);
//        switch to alert
//        click ok
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
//        switch to second alert
//        verify alert text
           assertEquals("Booooooooo!", driver.switchTo().alert().getText());
//        click ok on second alert
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
//        verify that the correct page is opened
        assertEquals(expectedPage, driver.getCurrentUrl());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
          WebElement goToAlertedPageBtn= driver.findElement(By.className("w3-blue"));
          goToAlertedPageBtn.click();
          Thread.sleep(1000);
//        switch to alert
          driver.switchTo().alert().dismiss();
          Thread.sleep(1000);
//        click cancel
          String textShown = "So you desided to say? Good!";
          assertEquals(textShown, driver.findElement(By.id("textForAlerts")).getText());
//        verify the text on page
    }
}
