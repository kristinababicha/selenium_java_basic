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
//         check that all field are empty and no ticks are clicked

        assertEquals("", driver.findElement(By.id("fb_name")).getText());
        assertEquals("", driver.findElement(By.id("fb_age")).getText());

        //checkbox
        List<WebElement> elements = driver.findElements(By.xpath("//*[@type=\"checkbox\"]"));
        assertFalse(elements.get(0).isSelected());
        assertFalse(elements.get(1).isSelected());
        assertFalse(elements.get(2).isSelected());
        assertFalse(elements.get(3).isSelected());
//RadioButton
        List<WebElement> elements1 = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        assertFalse(elements1.get(0).isSelected());
        assertFalse(elements1.get(1).isSelected());
//comments
       assertEquals("", driver.findElement(By.cssSelector("textarea.w3-input")).getText());
    //gender
        assertTrue(elements1.get(2).isSelected());


//         "Don't know" is selected in "Genre"
        assertTrue(elements1.get(2).isSelected());

//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters

        assertEquals("Choose your option", driver.findElement(By.xpath("//*[@id='like_us']/option[1]")).getText());

        assertEquals("rgba(33, 150, 243, 1)",driver.findElement(By.className("w3-blue")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",driver.findElement(By.className("w3-blue")).getCssValue("color"));

        }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//         check fields are empty or "null"
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertFalse(driver.findElement(By.xpath("//span[@id='gender']")).isSelected());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("Shiva");
        driver.findElement(By.id("fb_age")).sendKeys("26");
        driver.findElement(By.xpath("//*[@type=\"checkbox\"]")).click();
        driver.findElements(By.xpath("//*[@type=\"radio\"]")).get(1).click();
        driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='male']")).click();
        driver.findElement(By.cssSelector("textarea.w3-input")).sendKeys("hii");

        Select select = new Select(driver.findElement(By.xpath("//select[@name='option']")));
        select.selectByValue("Ok, i guess");

        Thread.sleep(5000);
        driver.findElement(By.className("w3-btn-block")).click();

//         check fields are filled correctly
        assertEquals("Shiva", driver.findElement(By.id("name")).getText());
        assertEquals("26", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.xpath("//span[@id='gender']")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals("hii", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
        driver.findElement(By.id("fb_name")).sendKeys("Shiva");
        driver.findElement(By.className("w3-btn-block")).click();
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Shiva, for your feedback!",driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
        driver.findElement(By.className("w3-btn-block")).click();
        driver.findElement(By.className("w3-green")).click();

        assertEquals("Thank you for your feedback!",driver.findElement(By.id("message")).getText());

        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
        //         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("Shiva");
        driver.findElement(By.id("fb_age")).sendKeys("26");
        driver.findElement(By.xpath("//*[@type=\"checkbox\"]")).click();
        driver.findElements(By.xpath("//*[@type=\"radio\"]")).get(1).click();
        driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='male']")).click();
        driver.findElement(By.cssSelector("textarea.w3-input")).sendKeys("hii");

//         click "Send"
        Thread.sleep(5000);
        driver.findElement(By.className("w3-btn-block")).click();

//         click "No"
        driver.findElement(By.className("w3-red")).click();

//         check fields are filled correctly
        driver.findElement(By.id("fb_name")).sendKeys("Shiva");
        driver.findElement(By.id("fb_age")).sendKeys("26");
        driver.findElement(By.xpath("//*[@type=\"checkbox\"]")).click();
        driver.findElements(By.xpath("//*[@type=\"radio\"]")).get(1).click();
        driver.findElement(By.xpath("//*[contains(@class, 'w3-radio') and @value='male']")).click();
        driver.findElement(By.cssSelector("textarea.w3-input")).sendKeys("hii");

    }
}
