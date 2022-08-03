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

//         check that all field are empty and no ticks are clicked
        assertTrue(driver.findElement(By.id("fb_name")).getAttribute("value").isEmpty());
        assertTrue(driver.findElement(By.id("fb_age")).getAttribute("value").isEmpty());
        assertTrue(driver.findElement(By.name("comment")).getAttribute("value").isEmpty());
        List<WebElement> checkBox = driver.findElements(By.className("w3-check"));

        for(WebElement check : checkBox){
            assertFalse(check.isSelected());
        }
//         "Don't know" is selected in "Genre"
        List<WebElement> radiobutton = driver.findElements(By.className("w3-radio"));
        assertFalse(radiobutton.get(0).isSelected());
        assertFalse(radiobutton.get(1).isSelected());
        assertTrue(radiobutton.get(2).isSelected());
//         "Choose your option" in "How do you like us?"
        Select select = new Select(driver.findElement(By.className("w3-select")));
        assertEquals("Choose your option",select.getAllSelectedOptions().get(0).getText());
//         check that the button send is blue with white letters
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        assertEquals("rgba(33, 150, 243, 1)", button.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", button.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {

//         click "Send" without entering any data
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//         check fields are empty or "null"
        assertTrue(driver.findElement(By.id("name")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("age")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("language")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("comment")).getText().isEmpty());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        WebElement noButton = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {

//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("Saule");
        driver.findElement(By.id("fb_age")).sendKeys("22");
        driver.findElements(By.className("w3-check")).get(0).click();
        driver.findElements(By.className("w3-radio")).get(1).click();
        Select select = new Select(driver.findElement(By.className("w3-select")));
        select.selectByIndex(2);
        driver.findElement(By.name("comment")).sendKeys("Testing");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//         check fields are filled correctly
        assertEquals("Saule", driver.findElement(By.id("name")).getText());
        assertEquals("22", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals("Testing", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        WebElement noButton = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {

//         enter only name
        String name = "Saule";
        driver.findElement(By.id("fb_name")).sendKeys(name);
//         click "Send"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        WebElement message = driver.findElement(By.xpath("//h2[@id='message']"));
        assertEquals("Thank you, "+name+", for your feedback!", message.getText());
//         color of text is white with green on the background
        WebElement panelColor = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", panelColor.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", panelColor.getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {

//         click "Send" (without entering anything
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you for your feedback!"
        WebElement message = driver.findElement(By.xpath("//h2[@id='message']"));
        assertEquals("Thank you for your feedback!", message.getText());
//         color of text is white with green on the background
        WebElement panelColor = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", panelColor.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", panelColor.getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {

//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("Saule");
        driver.findElement(By.id("fb_age")).sendKeys("22");
        driver.findElements(By.className("w3-check")).get(0).click();
        driver.findElements(By.className("w3-radio")).get(1).click();
        Select select = new Select(driver.findElement(By.className("w3-select")));
        select.selectByIndex(2);
        driver.findElement(By.name("comment")).sendKeys("Testing");
//         click "Send"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//         click "No"
        driver.findElement(By.className("w3-red")).click();
//         check fields are filled correctly
        assertEquals("Saule", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("22", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue(driver.findElements(By.className("w3-check")).get(0).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(1).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(2).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(3).isSelected());
        assertTrue(driver.findElements(By.className("w3-radio")).get(1).isSelected());
        assertEquals("Ok, i guess",select.getAllSelectedOptions().get(0).getText());
        assertEquals("Testing", driver.findElement(By.name("comment")).getAttribute("value"));
    }
}
