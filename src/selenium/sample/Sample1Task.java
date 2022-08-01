package selenium.sample;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {
//         // define driver
        WebDriver driver = new ChromeDriver();
//         go to https://kristinek.github.io/site/index2.html
        driver.get("https://kristinek.github.io/site/index2.html");
//         get title of page
        System.out.println(driver.getTitle());
//         get URL of current page
        System.out.println(driver.getCurrentUrl());
//         close browser
        driver.quit();
    }
}
