package selenium.sample;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() {
//        TODO:
//         define driver
//         go to https://kristinek.github.io/site/index2.html
//         get title of page
//         get URL of current page
//         close browser
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        ChromeDriver dr = new ChromeDriver();
        dr.get("https://kristinek.github.io/site/index2.html");

        System.out.println(dr.getTitle());
        System.out.println(dr.getCurrentUrl());
        dr.close();

    }
}
