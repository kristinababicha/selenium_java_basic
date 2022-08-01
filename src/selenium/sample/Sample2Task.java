package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

public class Sample2Task {
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
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
//
//         get text "Heading 2 text" using id
        String text = driver.findElement(By.id("heading_2")).getText();
        System.out.println(text);
    }

    @Test
    public void findElementByName() throws Exception {

//         get attribute "id" and "value" of button "This is also a button" using name
        WebElement ele = driver.findElement(By.name("randomButton2"));
        System.out.println(ele.getAttribute("id"));
        System.out.println(ele.getAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {

//         get first text of class "test" (should be "Test Text 1")

        System.out.println(driver.findElements(By.className("test")).get(0).getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {

//         get size text of class "test" (should be 5)
//         get text of class "test"
//         get third text of class "test" (should be "Test Text 4")
        System.out.println(driver.findElements(By.className("test")).size());
        System.out.println("--------------------------------");
        List<WebElement> elements = driver.findElements(By.className("test"));
        for (WebElement elem : elements){
            System.out.println(elem.getText());
        }
        System.out.println("--------------------------------");
        elements.forEach(webElement -> {
            System.out.println(webElement.getText());
        });
        System.out.println("--------------------------------");
        System.out.println(driver.findElements(By.className("test")).get(2).getText());
    }
}
