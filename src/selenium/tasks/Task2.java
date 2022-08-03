package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
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
        assertEquals("", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("fb_age")).getAttribute("value"));
        List<WebElement> checkboxes = driver.findElements(By.id("w3-check"));
        for (WebElement cbx : checkboxes) {
            assertFalse(cbx.isSelected());
        }
        List<WebElement> radios = driver.findElements(By.id("w3-radio"));
        for(WebElement rd : radios){
            if(rd.isEnabled()){
                assertFalse(rd.isSelected());
            }else{
                assertTrue(rd.isSelected());
            }
        }
        assertEquals("", driver.findElement(By.id("like_us")).getAttribute("value"));
        assertEquals("", driver.findElement(By.className("w3-input")).getAttribute("value"));
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.tagName("button")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.tagName("button")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
        driver.findElement(By.tagName("button")).click();
//        Thread.sleep(1000);
        List<WebElement> results = driver.findElements(By.tagName("span"));
        for(WebElement res: results){
            if(res.getText() != null && res.getText() == ""){
                assertEquals("", res.getText());
            }else if (res.getText() == null){
                assertEquals("null", res.getText());
            }
        }

        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        driver.findElement(By.id("fb_name")).sendKeys("Gandalf White");
        driver.findElement(By.id("fb_age")).sendKeys("30");
        driver.findElements(By.className("w3-check")).get(0).click();
        driver.findElements(By.className("w3-radio")).get(0).click();
        driver.findElements(By.tagName("option")).get(0).click();
        driver.findElements(By.cssSelector(".w3-select>option")).get(4).click();
        driver.findElement(By.cssSelector("textarea.w3-input")).sendKeys("Who came first: a chicken or an egg?");
        driver.findElement(By.tagName("button")).click();

        String list[] = {"Gandalf White", "30", "English", "male", "Why me?", "Who came first: a chicken or an egg?"};
        List<WebElement> results = driver.findElements(By.tagName("span"));
        for(int i=0; i<list.length; i++){
            assertEquals(list[i], results.get(i).getText());
        }
//        Thread.sleep(5000);
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        driver.findElement(By.id("fb_name")).sendKeys("Gandalf White");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.className("w3-green")).click();
        assertEquals("Thank you, Gandalf White, for your feedback!", driver.findElement(By.id("message")).getText());
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
//        Thread.sleep(5000);
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.className("w3-green")).click();
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        driver.findElement(By.id("fb_name")).sendKeys("Gandalf White");
        driver.findElement(By.id("fb_age")).sendKeys("30");
        driver.findElements(By.className("w3-check")).get(0).click();
        driver.findElements(By.className("w3-radio")).get(0).click();
        driver.findElements(By.tagName("option")).get(0).click();
        driver.findElements(By.cssSelector(".w3-select>option")).get(4).click();
        driver.findElement(By.cssSelector("textarea.w3-input")).sendKeys("Who came first: a chicken or an egg?");
        driver.findElement(By.tagName("button")).click();

        driver.findElement(By.className("w3-red")).click();

        assertEquals("Gandalf White",driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("30",driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertEquals("English", driver.findElements(By.className("w3-check")).get(0).getAttribute("value"));
        assertEquals("male",driver.findElements(By.className("w3-radio")).get(0).getAttribute("value"));
        assertEquals("Why me?",driver.findElements(By.cssSelector(".w3-select>option")).get(4).getText());
        assertEquals("Who came first: a chicken or an egg?", driver.findElement(By.cssSelector("textarea.w3-input")).getAttribute("value"));
    }
}
