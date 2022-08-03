package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
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

//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
        //Thread.sleep(5000);
//         * 2) check that button does not appear,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        Thread.sleep(10000);
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenImplicit() throws Exception {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//         * 2) check that button does not appear,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());

    }

    @Test
    public void loadGreenExplicitWait() throws Exception {

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,10).ignoring(StaleElementReferenceException.class);
//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//         * 2) check that button does not appear,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//          0) wait until button to load green and blue appears
//          1) click on start loading green and blue button
        driver.findElement(By.id("start_green_and_blue")).click();
//          2) check that button does not appear, but loading text is seen instead for green
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green_without_blue")).getText());
//          3) check that button does not appear, but loading text is seen instead for green and blue
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertEquals("Loading blue...", driver.findElement(By.id("loading_green_with_blue")).getText());
//          4) check that button and loading green does not appear,
//          		but loading text is seen instead for blue and success for green is seen
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertEquals("Green finished waiting for blue", driver.findElement(By.id("loading_blue_without_green")).getText());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
//          5) check that both button and loading text is not seen, success is seen instead
        assertEquals("Green and Blue Loaded", driver.findElement(By.id("finish_green_and_blue")).getText());
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());


       // 2 way
//        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));
//        driver.findElement(By.id("start_green_and_blue")).click();
//
//        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
//        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_green_with_blue")));
//        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
//        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
//        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_blue_without_green")));
//        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
//        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
//        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
//        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green_and_blue")));
//        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
//        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
//        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
//        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
//        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
    }

}