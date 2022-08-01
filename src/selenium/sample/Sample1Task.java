package selenium.sample;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
WebDriver driver = new ChromeDriver();

//         define driver
        driver.get("https://google.com");
driver.get("https://kristinek.github.io/site/index2.html");
//         get title of page
        System.out.println(driver.getTitle());
//         get URL of current page
        System.out.println(driver.getCurrentUrl());
//         close browser
        driver.quit();
    }
}
