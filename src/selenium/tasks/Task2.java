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
        WebElement nameArea = driver.findElement(By.id("fb_name"));
        WebElement ageArea = driver.findElement(By.id("fb_age"));
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class = 'w3-check']"));
        WebElement radioButton1 = driver.findElement(By.xpath("//input[@class = 'w3-radio' and @value = 'male']"));
        WebElement radioButton2 = driver.findElement(By.xpath("//input[@class = 'w3-radio' and @value = 'female']"));
        WebElement radioButton3 = driver.findElement(By.xpath("//input[@class = 'w3-radio' and @disabled]"));
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        WebElement commentArea = driver.findElement(By.xpath("//textarea[@name='comment']"));
        WebElement sendButton = driver.findElement(By.xpath("//button[@type = 'submit']"));

//         TODO:
//         check that all field are empty and no ticks are clicked
        assertEquals("", nameArea.getAttribute("value"));
        assertEquals("", ageArea.getAttribute("value"));
        for (WebElement cb: checkboxes) {
            assertFalse(cb.isSelected());
        }
        assertFalse(radioButton1.isSelected());
        assertFalse(radioButton2.isSelected());
        assertEquals("", commentArea.getAttribute("value"));
//         "Don't know" is selected in "Genre"
        assertTrue(radioButton3.isSelected());
//         "Choose your option" in "How do you like us?"
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
//         check fields are empty or "null"
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noButton = driver.findElement(By.xpath("//button[@onclick = 'window.history.back();']"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class = 'w3-check']"));
        WebElement radioButton2 = driver.findElement(By.xpath("//input[@class = 'w3-radio' and @value = 'female']"));
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        WebElement commentArea = driver.findElement(By.xpath("//textarea[@name='comment']"));
        WebElement sendButton = driver.findElement(By.xpath("//button[@type = 'submit']"));

        String tempName = "Tina";
        String tempAge = "34";
        String tempComment = "I am studying";
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys(tempName);
        driver.findElement(By.id("fb_age")).sendKeys(tempAge);
        checkboxes.get(0).click();
        radioButton2.click();
        dropdown.selectByVisibleText("Ok, i guess");
        commentArea.sendKeys(tempComment);
        sendButton.click();
//         check fields are filled correctly
        assertEquals(tempName, driver.findElement(By.id("name")).getText());
        assertEquals(tempAge, driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals(tempComment, driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noButton = driver.findElement(By.xpath("//button[@onclick = 'window.history.back();']"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
        WebElement sendButton = driver.findElement(By.xpath("//button[@type = 'submit']"));

        String tempName = "Tina";
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys(tempName);
//         click "Send"
        sendButton.click();
//         click "Yes"
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        yesButton.click();
//         check message text: "Thank you, NAME, for your feedback!"
        WebElement message = driver.findElement(By.id("message"));
        String expectedText = "Thank you, " + tempName + ", for your feedback!";
        assertEquals(expectedText, message.getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", message.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.xpath("//*[contains(@class, 'w3-panel')]"))
                        .getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
        WebElement sendButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
//         TODO:
//         click "Send" (without entering anything
        sendButton.click();
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
//         click "Yes"
        yesButton.click();
//         check message text: "Thank you for your feedback!"
        WebElement message = driver.findElement(By.id("message"));
        String expectedText = "Thank you for your feedback!";
        assertEquals(expectedText, message.getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", message.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.xpath("//*[contains(@class, 'w3-panel')]"))
                        .getCssValue("background-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {

        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class = 'w3-check']"));;
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        WebElement commentArea = driver.findElement(By.xpath("//textarea[@name='comment']"));

        String tempName = "Jana";
        String tempAge = "41";
        String tempComment = "I am working";
//         TODO:
//         fill the whole form

        driver.findElement(By.id("fb_name")).sendKeys(tempName);

        driver.findElement(By.id("fb_age")).sendKeys(tempAge);
        checkboxes.get(1).click();
        driver.findElement(By.xpath("//input[@class = 'w3-radio' and @value = 'female']")).click();
        dropdown.selectByVisibleText("Why me?");
        commentArea.sendKeys(tempComment);

//         click "Send"
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
//         click "No"
        WebElement noButton = driver.findElement(By.xpath("//button[@onclick = 'window.history.back();']"));
        noButton.click();
//         check fields are filled correctly

        assertEquals(tempName, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(tempAge, driver.findElement(By.id("fb_age")).getAttribute("value"));

        assertFalse(driver.findElements(By.className("w3-check")).get(0).isSelected());
        assertTrue(driver.findElements(By.className("w3-check")).get(1).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(2).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(3).isSelected());

        assertFalse(driver.findElements(By.className("w3-radio")).get(0).isSelected());
        assertTrue(driver.findElements(By.className("w3-radio")).get(1).isSelected());
        assertFalse(driver.findElements(By.className("w3-radio")).get(2).isSelected());

        dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Why me?", dropdown.getFirstSelectedOption().getText());

        assertEquals(
                tempComment,
                driver.findElement(By.xpath("//textarea[@name='comment']")).getAttribute("value"));

    }
}
