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
import java.util.concurrent.TimeUnit;

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
        assertTrue(driver.findElement(By.id("fb_name")).getAttribute("value").isEmpty());
        assertTrue(driver.findElement(By.id("fb_age")).getAttribute("value").isEmpty());
        assertTrue(driver.findElement(By.name("comment")).getAttribute("value").isEmpty());
        List<WebElement> checkBox = driver.findElements(By.className("w3-check"));

        for(WebElement check : checkBox){
            assertFalse(check.isSelected());
        }

//         "Don't know" is selected in "Genre"
        List<WebElement> elems = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        assertFalse(elems.get(0).isSelected());
        assertFalse(elems.get(1).isSelected());
        assertTrue(elems.get(2).isSelected());
//         "Choose your option" in "How do you like us?"
        Select select = new Select(driver.findElement(By.className("w3-select")));
        assertEquals("Choose your option",select.getAllSelectedOptions().get(0).getText());

        //Select dropdown = new Select(driver.findElement(By.xpath("//*[@class=\"w3-select\"]")));
        //driver.findElement(By.class("w3-select")).gettext();

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        assertEquals("rgba(33, 150, 243, 1)", button.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", button.getCssValue("color"));
//         check that the button send is blue with white letters
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         click "Send" without entering any data
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
//         check fields are empty or "null"
        assertTrue(driver.findElement(By.id("name")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("age")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("language")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("comment")).getText().isEmpty());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("Janis");
        driver.findElement(By.id("fb_age")).sendKeys("35");
        driver.findElement(By.xpath("//*[@value=\"English\"]")).click();
        driver.findElement(By.xpath("//*[@value=\"male\"]")).click();

        driver.findElement(By.id("like_us")).click();
        driver.findElement(By.xpath("//option[@value='Why me?']")).click();

        //Select dropdown = new Select(driver.findElement(By.id("like_us")));
        //    dropdown.selectByValue("Why_me?");

        driver.findElement(By.name("comment")).sendKeys("I am tierd");
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();

//         check fields are filled correctly
        assertEquals("Janis", driver.findElement(By.id("name")).getText());
        assertEquals("35", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Why me?", driver.findElement(By.id("option")).getText());
        assertEquals("I am tierd", driver.findElement(By.id("comment")).getText());
//         check button colors
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
//         (green with white letter and red with white letters)
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys("Janis");
//         click "Send"
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        String name = "Janis";
        assertEquals(("Thank you, " + name + ", for your feedback!"), driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals(("Thank you for your feedback!"), driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("Janis");
        driver.findElement(By.id("fb_age")).sendKeys("35");
        driver.findElement(By.xpath("//*[@value=\"English\"]")).click();
        driver.findElement(By.xpath("//*[@value=\"male\"]")).click();
        driver.findElement(By.id("like_us")).click();
        driver.findElement(By.xpath("//option[@value='Why me?']")).click();
        driver.findElement(By.name("comment")).sendKeys("I am tierd");
//         click "Send"
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
//         click "No"
        driver.findElement(By.className("w3-red")).click();
//         check fields are filled correctly
        assertEquals("Janis", driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value"));
        assertEquals("35", driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value"));
        assertTrue(driver.findElement(By.xpath("//input[@value='English']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//input[@value='male']")).isSelected());
        assertEquals("Why me?", driver.findElement(By.id("like_us")).getAttribute("value"));
        assertEquals("I am tierd", driver.findElement(By.xpath("//textarea[@name='comment']")).getAttribute("value"));
    }
}
