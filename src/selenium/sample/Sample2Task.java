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
//         TODO:
//         get text "Heading 2 text" using id
        System.out.println(driver.findElement(By.id("heading_1")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//         get attribute "id" and "value" of button "This is also a button" using name
        //System.out.println(driver.findElement(By.name("randomButton2"));
        WebElement element = driver.findElement(By.name("randomButton2"));
        System.out.println("ID - " + element.getAttribute("id"));
        System.out.println("VALUE - " + element.getAttribute("value"));

    //System.out.println(driver.findElement(By.id("buttonId").getattribute("value")));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//         get first text of class "test" (should be "Test Text 1")
        List<WebElement> elements= driver.findElements(By.className("test"));
        int i=1;
        for (WebElement allElements : elements) {
            if(i==1)
                System.out.println(allElements.getText());

            i++;
        }

     }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//         get size text of class "test" (should be 5)
        List<WebElement> elements= driver.findElements(By.className("test"));
        System.out.println("SIZE TEXT OF CLASS - "+elements.size());
        int i = 1;
        for (WebElement classAll:elements){
            //         get text of class "test"
            System.out.println("TEXT OF CLASS test "+ classAll.getText());
            if(i == 3) {
                //         get third text of class "test" (should be "Test Text 4")
                System.out.println("Third Text of Class test is " + classAll.getText());
            }
            i++;
        }



    }
}
