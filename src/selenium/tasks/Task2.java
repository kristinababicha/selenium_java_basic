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
        //check that name, phone fields are empty
        assertEquals("", driver.findElement(By.id("fb_name")).getText());
        assertEquals("", driver.findElement(By.id("fb_age")).getText());

        //check that checkboxes are not clicked
        List<WebElement> elements = driver.findElements(By.xpath("//*[@type=\"checkbox\"]"));
        assertFalse(elements.get(0).isSelected());
        assertFalse(elements.get(1).isSelected());
        assertFalse(elements.get(2).isSelected());
        assertFalse(elements.get(3).isSelected());

        //check that radiobuttons are not clicked
        List<WebElement> elementsr = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        assertFalse(elementsr.get(0).isSelected());
        assertFalse(elementsr.get(1).isSelected());

        //check that comment field is empty
        Thread.sleep(1000);
        assertEquals("", driver.findElement(By.cssSelector("textarea.w3-input")).getText());

        //"Don't know" is selected in "Genre"
        assertTrue(elementsr.get(2).isSelected());

        // "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.className("w3-blue")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-blue")).getCssValue("color"));
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

//         check button colors
// (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        String inputName = "Kristine";
        driver.findElement(By.id("fb_name")).sendKeys(inputName);
        String inputAge = "26";
        driver.findElement(By.id("fb_age")).sendKeys(inputAge);

        List<WebElement> elements = driver.findElements(By.xpath("//*[@type=\"checkbox\"]"));
        elements.get(0).click();
        List<WebElement> elementsr = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        elementsr.get(1).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        String inputText = "Thank you";
        driver.findElement(By.cssSelector("textarea.w3-input")).sendKeys(inputText);

        Thread.sleep(10000);
        driver.findElement(By.className("w3-btn-block")).click();

//         check fields are filled correctly
        assertEquals(inputName, driver.findElement(By.id("name")).getText());
        assertEquals(inputAge, driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Good", driver.findElement(By.id("option")).getText());
        assertEquals(inputText, driver.findElement(By.id("comment")).getText());

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
        String inputName = "Kristine";
        driver.findElement(By.id("fb_name")).sendKeys(inputName);
        driver.findElement(By.className("w3-btn-block")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, "+inputName+", for your feedback!", driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.className("w3-btn-block")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
        String inputName = "Kristine";
        driver.findElement(By.id("fb_name")).sendKeys(inputName);
        String inputAge = "26";
        driver.findElement(By.id("fb_age")).sendKeys(inputAge);

        List<WebElement> elements = driver.findElements(By.xpath("//*[@type=\"checkbox\"]"));
        elements.get(0).click();
        List<WebElement> elementsr = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        elementsr.get(1).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        String inputText = "Thank you";
        driver.findElement(By.cssSelector("textarea.w3-input")).sendKeys(inputText);

        Thread.sleep(10000);
        driver.findElement(By.className("w3-btn-block")).click();

//         click "No"
        driver.findElement(By.className("w3-red")).click();

//         check fields are filled correctly
        assertEquals(inputName, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(inputAge, driver.findElement(By.id("fb_age")).getAttribute("value"));

        assertTrue(elements.get(0).isSelected());
        assertFalse(elements.get(1).isSelected());
        assertFalse(elements.get(2).isSelected());
        assertFalse(elements.get(3).isSelected());

        assertFalse(elementsr.get(0).isSelected());
        assertTrue(elementsr.get(1).isSelected());
        assertFalse(elementsr.get(2).isSelected());

        assertEquals("Good", dropdown.getFirstSelectedOption().getText());
        assertEquals(inputText, driver.findElement(By.cssSelector("textarea.w3-input")).getAttribute("value"));

    }
}
