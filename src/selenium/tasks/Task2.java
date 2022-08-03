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
        WebElement name = driver.findElement(By.id("fb_name"));
        assertEquals("", name.getText());
        WebElement age = driver.findElement(By.id("fb_age"));
        assertEquals("", age.getText());

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-row[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        WebElement radio1 = driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'"));
        assertFalse(radio1.isSelected());
        WebElement radio2 = driver.findElement(By.cssSelector(".w3-radio[value='female'][type='radio'"));
        assertFalse(radio2.isSelected());

//         "Don't know" is selected in "Genre"
        List<WebElement> elems = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        assertTrue(elems.get(2).isSelected());

//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)",
                driver.findElement(By.className("w3-blue")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.tagName("button")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.tagName("button")).click();

//         check fields are empty or "null"
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("background-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Jelena");

        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys("40");

        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checkBoxes.get(0).click();

        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
        radioButtons.get(1).click();

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Ok, i guess");

        WebElement comment = driver.findElement(By.name("comment"));
        comment.sendKeys("Have a nice day!");
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(1000);

//         check fields are filled correctly
        assertEquals("Jelena", driver.findElement(By.id("name")).getText());
        assertEquals("40", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals("Have a nice day!", driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Jelena");

//         click "Send"
        driver.findElement(By.tagName("button")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Jelena, for your feedback!", driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.tagName("button")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Jelena");

        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys("40");

        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checkBoxes.get(0).click();

        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
        radioButtons.get(1).click();

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Ok, i guess");

        WebElement comment = driver.findElement(By.name("comment"));
        comment.sendKeys("Have a nice day!");

//         click "Send"
        driver.findElement(By.tagName("button")).click();

//         click "No"
        driver.findElement(By.className("w3-red")).click();

//         check fields are filled correctly
        assertEquals("Jelena", name.getAttribute("value"));
        assertEquals("40", age.getAttribute("value"));
        assertTrue(checkBoxes.get(0).isSelected());
        assertTrue(radioButtons.get(1).isSelected());
        assertEquals("Ok, i guess", driver.findElement(By.id("like_us")).getAttribute("value"));
        assertEquals("Have a nice day!", comment.getAttribute("value"));
    }
}
