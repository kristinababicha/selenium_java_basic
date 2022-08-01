package selenium.sample;


import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * @author vigneshKrishnan
 */

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @BeforeClass
    public static void before() {
        System.setProperty("webdriver.chrome.driver",libWithDriversLocation+"chromedriver");
    }

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
        WebDriver driver = new ChromeDriver();

//         go to https://kristinek.github.io/site/index2.html
        driver.get("https://kristinek.github.io/site/index2.html");

//         get title of page
        System.out.println("Title of the Page is "+driver.getTitle());
//         get URL of current page
        System.out.println("URL of the Current page is "+driver.getCurrentUrl());
//         close browser
        driver.close();
    }
}
