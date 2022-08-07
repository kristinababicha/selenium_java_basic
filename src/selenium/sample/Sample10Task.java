package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.ColorSamplePage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Sample10Task {
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
//         TODO:
//         Use page object ColorSamplePage
//         * 1) click on start loading green button
        colorPage.clickStartLoadingGreen();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        colorPage.checkStartLoadingGreenBtnNotVisible();
        colorPage.checkLoadingGreenIsVisible();
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        colorPage.checkStartLoadingGreenBtnNotVisible();
        wait.until(ExpectedConditions.invisibilityOf(colorPage.getLoadingGreen()));
        colorPage.checkLoadingGreenIsNOTVisible();
        colorPage.checkGreenLoadedTxtIsVisible();
    }

}