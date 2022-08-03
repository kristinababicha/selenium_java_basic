package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
//         check that all field are empty and no ticks are clicked

        assertEquals("", driver.findElement(By.id("fb_name")).getText());
        assertEquals(null, driver.findElement(By.id("fb_age")).getAttribute("age"));

        List<WebElement> checkBox = driver.findElements(By.cssSelector("[type='checkbox']"));
        for (WebElement checkBoxNotSelected : checkBox) {
            assertFalse(checkBoxNotSelected.isSelected());
        }

        assertFalse(driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='male']")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='female']")).isSelected());

//         "Don't know" is selected in "Genre"
        assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='']")).isSelected());

//         "Choose your option" in "How do you like us?"
        assertEquals("Choose your option", driver.findElement(By.xpath("//*[@id='like_us']/option[1]")).getText());

        assertEquals("", driver.findElement(By.xpath("*//textarea")).getText());

//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.className("w3-btn-block")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-btn-block")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.className("w3-btn-block")).click();

//         check fields are empty or "null"
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());


//         check button colors:
//         green with white letter
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

//         and red with white letters
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("John");
        driver.findElement(By.id("fb_age")).sendKeys("123");
        driver.findElement(By.cssSelector(".w3-check[value='English']")).click();
        driver.findElement(By.xpath("//*[@id='like_us']/option[2]")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='male']")).click();
        driver.findElement(By.xpath("*//textarea")).sendKeys("asdasdasdasd");
        driver.findElement(By.className("w3-btn-block")).click();

//         check fields are filled correctly
        assertEquals("John", driver.findElement(By.id("name")).getText());
        assertEquals("123", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Good", driver.findElement(By.id("option")).getText());
        assertEquals("asdasdasdasd", driver.findElement(By.id("comment")).getText());

//         check button colors:
//         green with white letter
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

//         and red with white letters
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys("John");

//         click "Send"
        driver.findElement(By.className("w3-btn-block")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, John, for your feedback!", driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.className("w3-btn-block")).click();

//         click "Yes".
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("John");
        driver.findElement(By.id("fb_age")).sendKeys("123");
        driver.findElement(By.cssSelector(".w3-check[value='English']")).click();
        driver.findElement(By.xpath("//*[@id='like_us']/option[2]")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='male']")).click();
        driver.findElement(By.xpath("*//textarea")).sendKeys("asdasdasdasd");

//         click "Send"
        driver.findElement(By.className("w3-btn-block")).click();

//         click "No"
        driver.findElement(By.className("w3-red")).click();

//         check fields are filled correctly
        assertEquals("John", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("123", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue(driver.findElement(By.cssSelector(".w3-check[value='English']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='male']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[@id='like_us']/option[2]")).isSelected());
        assertEquals("asdasdasdasd", driver.findElement(By.xpath("*//textarea")).getAttribute("value"));
    }
}
