package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();

        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

//        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

        Thread.sleep(6000);

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());

//        assertEquals("", driver.findElement(By.id("loading_green")).getText());
//        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed()); //wait time is triggered here! this element appears last
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());



    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10);

//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
    }

}