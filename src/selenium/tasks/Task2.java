package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:


//         check that all field are empty and no ticks are clicked/asserts
        assertEquals("", driver.findElement(By.id("fb_name")).getText());
        assertEquals("", driver.findElement(By.className("w3-rest")).getText());
        assertEquals("", driver.findElement(By.className("w3-check")).getText());
        assertEquals("", driver.findElement(By.className("w3-radio")).getText());
        assertEquals("", driver.findElement(By.className("w3-input")).getText());

//         "Don't know" is selected in "Genre"/asserts
        List<WebElement> elems = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        for (WebElement elem : elems) {
            assertTrue(elems.get(2).isSelected());
        }
//        assertEquals("Don't know", driver.findElement(By.className("w3-radio")).getText());


//         "Choose your option" in "How do you like us?" //as we did with radio buttons
        Select dropdown = new Select(driver.findElement(By.className("w3-select")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

        assertEquals("rgba(33, 150, 243, 1)" ,
                driver.findElement(By.className("w3-btn-block")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)" ,
                driver.findElement(By.className("w3-btn-block")).getCssValue("color"));

        }

//         check that the button send is blue with white letters //check color and backgorund color
//}

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.className("w3-btn-block")).click();
//         check fields are empty or "null" (null is a string)- has no value
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());


//         check button colors
        //green button
        assertEquals("rgba(76, 175, 80, 1)" ,
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)" ,
                driver.findElement(By.className("w3-green")).getCssValue("color"));
        //red button
        assertEquals("rgba(244, 67, 54, 1)" ,
                driver.findElement(By.className("w3-red")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)" ,
                driver.findElement(By.className("w3-red")).getCssValue("color"));


//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("kristina");
        driver.findElement(By.id("fb_age")).sendKeys("64");
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//*[@type=\"checkbox\"]"));
        checkBoxes.get(0).click();
        List<WebElement> elems = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        elems.get(1).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        Thread.sleep(5000);
        dropdown.selectByVisibleText("Good");
        driver.findElement(By.name("comment")).sendKeys("Hi Janis!");
        Thread.sleep(5000);
        driver.findElement(By.className("w3-btn-block")).click();


//         check fields are filled correctly, asssert that those value appear in the new page
        assertEquals("kristina", driver.findElement(By.id("name")).getText());
        assertEquals("64", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Good", driver.findElement(By.id("option")).getText());
        assertEquals("Hi Janis!", driver.findElement(By.id("comment")).getText());
//         check button colors
        //green button
        assertEquals("rgba(76, 175, 80, 1)" ,
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)" ,
                driver.findElement(By.className("w3-green")).getCssValue("color"));
        //red button
        assertEquals("rgba(244, 67, 54, 1)" ,
                driver.findElement(By.className("w3-red")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)" ,
                driver.findElement(By.className("w3-red")).getCssValue("color"));


    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:

//         enter only name

        driver.findElement(By.id("fb_name")).sendKeys("kristina");
//         click "Send"
        driver.findElement(By.className("w3-btn-block")).click();
        Thread.sleep(5000);
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you, NAME, for your feedback!"/asserts
        assertEquals("Thank you, kristina, for your feedback!",driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background/asserts
        assertEquals("rgba(76, 175, 80, 1)" ,
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)" ,
                driver.findElement(By.className("w3-green")).getCssValue("color"));


    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.className("w3-btn-block")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();


//         check message text: "Thank you for your feedback!"/asserts
        assertEquals("Thank you for your feedback!",driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)" ,
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)" ,
                driver.findElement(By.className("w3-green")).getCssValue("color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("kristina");
        driver.findElement(By.id("fb_age")).sendKeys("64");
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//*[@type=\"checkbox\"]"));
        checkBoxes.get(0).click();
        List<WebElement> elems = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        elems.get(1).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        driver.findElement(By.name("comment")).sendKeys("Hi Janis!");


//         click "Send"
        driver.findElement(By.className("w3-btn-block")).click();
//         click "No"
        driver.findElement(By.className("w3-red")).click();



//         check fields are filled correctly
        assertEquals("kristina", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("64", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue(checkBoxes.get(0).isSelected());
        assertTrue(elems.get(1).isSelected());
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());
        assertEquals("Hi Janis!", driver.findElement(By.name("comment")).getAttribute("value"));


    }
}
