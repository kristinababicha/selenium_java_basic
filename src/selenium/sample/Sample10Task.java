package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.ColorSamplePage;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Sample10Task extends ColorSamplePage {
    static WebDriver driver;
    static ColorSamplePage colorPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://kristinek.github.io/site/examples/loading_color");
        colorPage = PageFactory.initElements(driver, ColorSamplePage.class);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         Use page object ColorSamplePage
//         * 1) click on start loading green button
        colorPage.clickStartLoadingGreen();

//         * 2) check that button does not appear,
        colorPage.assertStartLoadingGreenNotVisible();
//         * but loading text is seen instead   "Loading green..."
        assertTrue(colorPage.LoadingGreenIsVisible());

//         * 3) check that both button
        colorPage.assertFinishGreenIsVisible();
//         * and loading text is not seen,
        colorPage.assertStartLoadingGreenNotVisible();
//         * success is seen instead "Green Loaded"
        assertFalse(colorPage.LoadingGreenIsVisible());
    }

}