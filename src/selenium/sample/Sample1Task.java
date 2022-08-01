package selenium.sample;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
        // does not work without line nr 18
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        WebDriver webDriver = new ChromeDriver();
//         go to https://kristinek.github.io/site/index2.html
        webDriver.get("https://kristinek.github.io/site/");
//         get title of page
        System.out.println(webDriver.getTitle());
//         get URL of current page
        System.out.println(webDriver.getCurrentUrl());
//         close browser
        webDriver.quit();
    }
}
