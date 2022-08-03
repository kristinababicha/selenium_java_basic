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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

//         * 2) check that button does not appear
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        //but loading text is seen instead "Loading green..."
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        //Wait for element to load
        Thread.sleep(5000);

//         * 3) check that both button and loading text is not seen
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        //success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        //Waiting time to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//         TODO:
//       * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();

//       * 2) check that button does not appear
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        //but loading text is seen instead "Loading green..."
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());


//       * 3) check that both button and loading text is not seen, success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        //The sequence is important due to the fact that
        //first element on this list should be the one that does not exist in the very beginning
        //then we look for the element which disappears withing the whole process

    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        //Wait to load
        WebDriverWait wait = new WebDriverWait(driver, 10);

//         TODO:
//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();


//         * 2) check that button does not appear, but loading text is seen instead "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());


//         * 3) check that both button and loading text is not seen,  success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());


    }

    @Test
    public void loadGreenAndBlueBonus() {
        TODO:
        // 0) wait until button to load green and blue appears
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //1) click on start loading green and blue button
        driver.findElement(By.id("start_green_and_blue")).click();


        // 2) check that button does not appear, but loading text is seen instead for green
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());


        //3) check that button does not appear, but loading text is seen instead for green and blue
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

        //4) check that button and loading green does not appear,
        // but loading text is seen instead for blue and success for green is seen
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());


        //5) check that both button and loading text is not seen, success is seen instead
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus2() {
        // 0) wait until button to load green and blue appears
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

        //1) click on start loading green and blue button
        driver.findElement(By.id("start_green_and_blue")).click();


        // 2) check that button does not appear, but loading text is seen instead for green
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

        //waiting time for green
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_green_with_blue")));


        //3) check that button does not appear, but loading text is seen instead for green and blue
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

        //waiting time for green
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_blue_without_green")));

        //4) check that button and loading green does not appear,
        // but loading text is seen instead for blue and success for green is seen
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());

        //waiting time for green
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green_and_blue")));


        //5) check that both button and loading text is not seen, success is seen instead
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());


    }

}