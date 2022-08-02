package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample6Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
        System.out.println("Using xPath");
//        1-2 ways to find text: "Heading 2 text":
        System.out.println(driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
                                                        //h2[contains(text(), '2')]
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.xpath("//p[.='Test Text 1']")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.xpath("//p[.='Test Text 2']")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.xpath("//p[.='Test Text 3']")).getText());
//        1-2 ways to find text: "Test Text 4"
        System.out.println(driver.findElement(By.xpath("//p[.='Test Text 4']")).getText());
//        1-2 ways to find text: "Test Text 5"
        System.out.println(driver.findElement(By.xpath("//p[.='Test Text 5']")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.xpath("//input[@id='buttonId']")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
        System.out.println("Using cssSelector");
//        1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.cssSelector("#test1>p.test")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector("#test1>p.twoTest")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.cssSelector("#test3>p.test")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));
    }
}
