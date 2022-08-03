package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebElement grBtn = driver.findElement(By.cssSelector("#start_green"));
        WebElement loadingGreen;
        WebElement loadedTXT;
//         * 1) click on start loading green button
        grBtn.click();
        Thread.sleep(500); // wait for loading to display and button to hide

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(grBtn.isDisplayed());
        loadingGreen = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        Thread.sleep(5000); // wait for loading to complete

//         * 3) check that both button
//         * and loading text is not seen,
//        assertTrue(loadedTXT.isDisplayed());
//         * success is seen instead "Green Loaded"
        loadedTXT = driver.findElement(By.cssSelector("#finish_green"));
        assertFalse(grBtn.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(loadedTXT.isDisplayed());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement grBtn = driver.findElement(By.cssSelector("#start_green"));
//         * 1) click on start loading green button
        grBtn.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."

        WebElement loadingGreen = driver.findElement(By.cssSelector("#loading_green"));
        assertFalse(grBtn.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement finishText = driver.findElement(By.cssSelector("#finish_green"));
        assertFalse(grBtn.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(finishText.isDisplayed());

    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String finishTextCSSSelector = "#finish_green";

        WebElement grBtn = driver.findElement(By.cssSelector("#start_green"));
//         * 1) click on start loading green button
        grBtn.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."

        WebElement loadingGreen = driver.findElement(By.cssSelector("#loading_green"));
        assertFalse(grBtn.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(finishTextCSSSelector)));

        WebElement finishText = driver.findElement(By.cssSelector(finishTextCSSSelector));
        assertFalse(grBtn.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(finishText.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {

        WebElement btnStart;
        WebElement loadingGreenWithoutBlue;
        WebElement loadingGreenWithBlue;
        WebElement loadingBlueWithoutGreen;
        WebElement resultGreenBlue;
        WebDriverWait wait = new WebDriverWait(driver, 10);
//        TODO:
//        0) wait until button to load green and blue appears
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        btnStart = driver.findElement(By.cssSelector("#start_green_and_blue"));

//        1) click on start loading green and blue button
        btnStart.click();

//        2) check that button does not appear, but loading text is seen instead for green
        loadingGreenWithoutBlue = driver.findElement(By.cssSelector("#loading_green_without_blue"));
        assertFalse(btnStart.isDisplayed());
        assertTrue(loadingGreenWithoutBlue.isDisplayed());

//        3) check that button does not appear, but loading text for green and blue is seen
        loadingGreenWithBlue = driver.findElement(By.cssSelector("#loading_green_with_blue"));
        assertFalse(btnStart.isDisplayed());
        assertTrue(loadingGreenWithoutBlue.isDisplayed());
        assertTrue(loadingGreenWithBlue.isDisplayed());

//        4) check that button and loading green does not appear,
//        		but loading text is seen instead for blue and success for green is seen
        wait.until(ExpectedConditions.invisibilityOf(loadingGreenWithoutBlue));
        assertFalse(btnStart.isDisplayed());
        assertFalse(loadingGreenWithoutBlue.isDisplayed());
        loadingBlueWithoutGreen = driver.findElement(By.cssSelector("#loading_blue_without_green"));
        assertTrue(loadingBlueWithoutGreen.isDisplayed());


//        5) check that both button and loading text is not seen, success is seen instead
        resultGreenBlue = driver.findElement(By.cssSelector("#finish_green_and_blue"));
        assertFalse(btnStart.isDisplayed());
        assertFalse(loadingGreenWithoutBlue.isDisplayed());
        assertFalse(loadingBlueWithoutGreen.isDisplayed());
        assertFalse(loadingGreenWithBlue.isDisplayed());
        assertTrue(resultGreenBlue.isDisplayed());
    }

}