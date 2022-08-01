package selenium.sample;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {

    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {

//        TODO
//         define driver
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        WebDriver newDriver = new ChromeDriver();
//         go to https://kristinek.github.io/site/index2.html
        newDriver.get("https://kristinek.github.io/site/index2.html");
//         get title of page
        System.out.println(newDriver.getTitle());
//         get URL of current page
        System.out.println(newDriver.getCurrentUrl());
//         close browser
        newDriver.quit();
    }
}
