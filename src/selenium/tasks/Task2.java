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
//          check that all fields are empty and no ticks are clicked
//          "Don't know" is selected in "Genre"
//          "Choose your option" in "How do you like us?"
//          check that the button send is blue with white letters
        List<WebElement> fields = driver.findElements(By.className("w3-input"));
        List<WebElement> checkboxes = driver.findElements(By.className("w3-check"));

        for (WebElement field : fields) {
            assertTrue(field.getText().isEmpty());
        }

        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }

        assertTrue(driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(4) > input:nth-child(6)")).isSelected());
        assertEquals("Choose your option", driver.findElement(By.cssSelector("#like_us > option:nth-child(1)")).getText());

        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.className("w3-blue")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-blue")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//          click "Send" without entering any data
//          check fields are empty or "null"
//          check button colors
//          (green with white letter and red with white letters)
        driver.findElement(By.className("w3-blue")).click();
        List<WebElement> spans = driver.findElements(By.xpath("//span"));
        for (WebElement span : spans) {
            assertTrue(span.getText().isEmpty() || span.getText().equals("null"));
        }

        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//          fill the whole form, click "Send"
//          check fields are filled correctly
//          check button colors
//          (green with white letter and red with white letters)
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Karline");
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys("23");
        driver.findElement(By.xpath("//input[@value='English']")).click();
        driver.findElement(By.xpath("//input[@value='female']")).click();
        driver.findElement(By.id("like_us")).click();
        driver.findElement(By.xpath("//option[@value='Ok, i guess']")).click();
        driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("No comments");

        driver.findElement(By.className("w3-blue")).click();

        assertEquals("Karline", driver.findElement(By.id("name")).getText());
        assertEquals("23", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("Option")).getText());
        assertEquals("No comments", driver.findElement(By.id("comment")).getText());

        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//          enter only name
//          click "Send"
//          click "Yes"
//          check message text: "Thank you, NAME, for your feedback!"
//          color of text is white with green on the background
        String name = "Karlīne";
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(name);
        driver.findElement(By.className("w3-blue")).click();
        driver.findElement(By.className("w3-green")).click();

        assertEquals(("Thank you, " + name + ", for your feedback!"), driver.findElement(By.id("message")).getText());
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//          click "Send" (without entering anything
//          click "Yes"
//          check message text: "Thank you for your feedback!"
//          color of text is white with green on the background
        driver.findElement(By.className("w3-blue")).click();
        driver.findElement(By.className("w3-green")).click();
        assertEquals(("Thank you for your feedback!"), driver.findElement(By.id("message")).getText());
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//          fill the whole form
//          click "Send"
//          click "No"
//          check fields are filled correctly

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Karline");
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys("23");
        driver.findElement(By.xpath("//input[@value='English']")).click();
        driver.findElement(By.xpath("//input[@value='female']")).click();
        driver.findElement(By.id("like_us")).click();
        driver.findElement(By.xpath("//option[@value='Ok, i guess']")).click();
        driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("No comments");

        driver.findElement(By.className("w3-blue")).click();
        driver.findElement(By.className("w3-red")).click();

        assertEquals("Karline", driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value"));
        assertEquals("23", driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value"));
        assertTrue(driver.findElement(By.xpath("//input[@value='English']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//input[@value='female']")).isSelected());
        assertEquals("Ok, i guess", driver.findElement(By.id("like_us")).getAttribute("value"));
        assertEquals("No comments", driver.findElement(By.xpath("//textarea[@name='comment']")).getAttribute("value"));
    }
}
