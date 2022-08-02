package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

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
        driver.findElement(By.className("w3-blue")).click();

//        click on "To go to alerted page press Ok. Or stay here" button
        Alert alert = driver.switchTo().alert();
//        switch to alert
        alert.accept();
//        click ok
        Alert alertOne = driver.switchTo().alert();

//        switch to second alert
        assertEquals("Booooooooo!",alertOne.getText() );
        System.out.println(alertOne.getText());
//        verify alert text
        alertOne.accept();

//        click ok on second alert
        assertEquals("https://kristinek.github.io/site/examples/alerted_page", driver.getCurrentUrl());
//        verify that the correct page is opened
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:

        driver.findElement(By.className("w3-blue")).click();
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.switchTo().alert().dismiss();

//        switch to alert
//        click cancel
        String displayedText = "So you desided to say? Good!";
        assertEquals(displayedText , driver.findElement(By.id("textForAlerts")).getText());
        // we added .getText(); in the end, because driver didnt recognise that element is text.
//        verify the text on page
    }
}
