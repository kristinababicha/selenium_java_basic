package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.TaskTwoPage;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;
    public TaskTwoPage taskPage;
    String name = "Vita";
    String age = "30";
    String comment = "Joey doesn't share food";

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        taskPage = PageFactory.initElements(driver, TaskTwoPage.class);
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
        List<WebElement> checkBoxes = driver.findElements(By.id("lang_check"));
        assertFalse(driver.findElement(By.id("lang_check")).isSelected());
//         "Don't know" is selected in "Genre"
        assertFalse(driver.findElement(By.xpath("//label[text()=\"Don't know (Disabled)\"]")).isSelected());
//         "Choose your option" in "How do you like us?"
        Select dropDown = new Select(driver.findElement(By.id("like_us")));
        dropDown.selectByVisibleText("Choose your option");
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.className("w3-btn-block")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-btn-block")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        taskPage.clickSend();
//         check fields are empty or "null"
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());
//         check button colors (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(age);
        driver.findElement(By.name("comment")).sendKeys(comment);
        taskPage.selectLanguage(0);
        taskPage.selectGender(1);
        taskPage.selectValueFromLikeUsTable("Good");
        taskPage.clickSend();
//         check fields are filled correctly
        assertEquals(name, driver.findElement(By.id("name")).getText());
        assertEquals(age, driver.findElement(By.id("age")).getText());
        assertEquals(comment, driver.findElement(By.id("comment")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Good", driver.findElement(By.id("option")).getText());
//         check button colors (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
//         click "Send"
        taskPage.clickSend();
//         click "Yes"
        taskPage.clickYes();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, " + name + ", for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-panel")).getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        taskPage.clickSend();
//         click "Yes"
        taskPage.clickYes();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-panel")).getCssValue("background-color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(age);
        driver.findElement(By.name("comment")).clear();
        driver.findElement(By.name("comment")).sendKeys(comment);
        taskPage.selectLanguage(0);
        taskPage.selectGender(1);
        taskPage.selectValueFromLikeUsTable("Good");
//         click "Send"
        taskPage.clickSend();
//         click "No"
        taskPage.clickNo();
//         check fields are filled correctly
        assertEquals(name, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(age, driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertEquals(comment, driver.findElement(By.name("comment")).getAttribute("value"));
        assertTrue(driver.findElements(By.className("w3-check")).get(0).isSelected());
        assertTrue(driver.findElements(By.className("w3-radio")).get(1).isSelected());
        assertTrue(driver.findElements(By.cssSelector(".w3-select>option")).get(1).isSelected());
    }
}
