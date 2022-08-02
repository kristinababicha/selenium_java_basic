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

//        driver.findElement(By.xpath("//h2[@id='heading_2']"));
//        System.out.println("Find element by id using xPath:");
//        System.out.println("\t text of element with id 'heading_1' is '" +
//                driver.findElement(By.xpath("//*[@id='heading_2']")).getText());

        // we have banch of element with class "test"
        // we will find parent element, and than select child with unique class
        System.out.println(driver.findElement(By.xpath("//*[@id=\"test1\"]/*[@class=\"test\"]")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@id=\"test1\"]/*[contains(text(),\"2\")]")).getText());

        System.out.println(driver.findElements(By.xpath("//div[@id=\"test3\"]/*")).get(0).getText());

        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@class=\"Test\"]")).getText());

        System.out.println(driver.findElement(By.xpath("//input[@name=\"randomButton2\"]")).getText());


//        1-2 ways to find text: "Heading 2 text":

//        1-2 ways to find text: "Test Text 1"

//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"

//        1-2 ways to find text: "Test Text 4"
//        1-2 ways to find text: "Test Text 5"
//        1-2 ways to find text: "This is also a button"
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.cssSelector("#test1 .test")).getText());

//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector(".twoTest")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.cssSelector("#test3 > p.test:nth-child(1)")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("[name=randomButton2]")).getText());
    }
}
