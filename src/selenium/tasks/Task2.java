package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import javax.xml.bind.Element;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertFalse;

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
//      check that all field are empty and no ticks are clicked
        driver.findElement(By.className("w3-input")).clear(); //checking fields: name, age and comment

        List<WebElement> checkBoxes = driver.findElements(By.className("w3-check")); //checking checkbox fields
        for (WebElement checkbox : checkBoxes) {
            assertFalse(checkbox.isSelected());
        }

        //checking dropdown fields
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

//         "Don't know" is selected in "Genre"
        assertTrue(driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(4) > input:nth-child(6)")).isSelected());


//         "Choose your option" in "How do you like us?"
        assertEquals("Choose your option", dropdown.getAllSelectedOptions().get(0).getText());
//
//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));


    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.className("w3-btn-block")).click();

//      check fields are empty or "null" /null is a String int this case
        assertTrue(driver.findElement(By.id("name")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("age")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("language")).getText().isEmpty());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertTrue(driver.findElement(By.id("comment")).getText().isEmpty());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement greenButton = driver.findElement(By.className("w3-green"));
        WebElement redButton = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(255, 255, 255, 1)", greenButton.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", greenButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", redButton.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", redButton.getCssValue("background-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"/ any values
        driver.findElement(By.id("fb_name")).sendKeys("Liene"); //fill in the name
        driver.findElement(By.id("fb_age")).sendKeys("28"); //fill in the age
        driver.findElement(By.xpath("//*[@value=\"English\"]")).click();
        driver.findElement(By.xpath("//*[@value=\"female\"]")).click();

        driver.findElement(By.id("like_us")).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByValue("Good");
        driver.findElement(By.name("comment")).sendKeys("Have a good day!");

        //click send button
        driver.findElement(By.className("w3-btn-block")).click();

//         check fields are filled correctly/ validate after pressing button
        assertEquals("Liene", driver.findElement(By.id("name")).getText());
        assertEquals("28", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Good", driver.findElement(By.id("option")).getText());
        assertEquals("Have a good day!", driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        WebElement noButton = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        String name = "Liene";
        driver.findElement(By.id("fb_name")).sendKeys(name);

//         click "Send"
        driver.findElement(By.className("w3-btn-block")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();


//         check message text: "Thank you, NAME, for your feedback!"
        WebElement yesMessage = driver.findElement(By.id("message"));
        assertEquals("Thank you, "+name+", for your feedback!", yesMessage.getText());


//         color of text is white with green on the background
        WebElement message = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(255, 255, 255, 1)", message.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", message.getCssValue("background-color"));

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.className("w3-btn-block")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you for your feedback!"
        WebElement emptyMessage = driver.findElement(By.id("message"));
        assertEquals("Thank you for your feedback!", emptyMessage.getText());

//         color of text is white with green on the background
        WebElement text = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(255, 255, 255, 1)", text.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", text.getCssValue("background-color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("Liene"); //fill in the name
        driver.findElement(By.id("fb_age")).sendKeys("28"); //fill in the age
        driver.findElement(By.xpath("//*[@value=\"English\"]")).click();
        driver.findElement(By.xpath("//*[@value=\"female\"]")).click();

        driver.findElement(By.id("like_us")).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByValue("Good");
        driver.findElement(By.name("comment")).sendKeys("Have a good day!");

//         click "Send"
        driver.findElement(By.className("w3-btn-block")).click();

//         click "No"
        driver.findElement(By.className("w3-red")).click();

//         check fields are filled correctly
        assertEquals("Liene", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("28", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue("English", driver.findElement(By.xpath("//*[@value=\"English\"]")).isSelected());
        assertTrue("female", driver.findElement(By.xpath("//*[@value=\"female\"]")).isSelected());
        assertTrue("Good", driver.findElement(By.xpath("//*[@id='like_us']/option[2]")).isSelected());
        assertEquals("Have a good day!", driver.findElement(By.name("comment")).getAttribute("value"));
    }
}
