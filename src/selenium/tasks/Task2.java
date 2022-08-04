package selenium.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static com.sun.xml.internal.fastinfoset.stax.events.Util.isEmptyString;
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
        WebElement name =driver.findElement(By.id("fb_name"));
        assertEquals("",name.getText());
        WebElement age =driver.findElement(By.id("fb_age"));
        assertEquals("",name.getText());
        //For language  none of the checkboxes are ticked
        assertFalse(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[2]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[4]")).isSelected());
        //For Grnre  none of the radiobutton (male or female only are ticked)
        //Dont Know disabled

        assertFalse(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).isSelected());

//for Gender
//         "Don't know" is selected in "Genre"
        assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]")).isSelected());
//         "Choose your option" in "How do you like us?"
        assertEquals("Choose your option",
                driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[1]")).getText());
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)",
                driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
        //Thread.sleep(2000);
//         check fields are empty or "null"
      // assertTrue(driver.findElement(By.id("name")).getText().isEmpty());
        assertEquals("",driver.findElement(By.id("name")).getText());
        assertEquals("",driver.findElement(By.id("age")).getText());
        assertEquals("",driver.findElement(By.id("language")).getText());
        assertEquals("null",driver.findElement(By.id("gender")).getText());
        assertEquals("null",driver.findElement(By.id("option")).getText());
        assertEquals("",driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)"
                ,driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)"
                ,driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("color"));
    }


    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        WebElement name = driver.findElement(By.name("name"));
        name.sendKeys("Adam");
        WebElement age = driver.findElement(By.name("age"));
        age.sendKeys("20");
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        WebElement comment = driver.findElement(By.name("comment"));
        comment.sendKeys("Test For Test ");
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
        Thread.sleep(1000);
        //         check fields are filled correctly
        assertEquals("Adam",driver.findElement(By.id("name")).getText());
        assertEquals("20",driver.findElement(By.id("age")).getText());
        assertEquals("English",driver.findElement(By.id("language")).getText());
        assertEquals("male",driver.findElement(By.id("gender")).getText());
        assertEquals("Good",driver.findElement(By.id("option")).getText());
        assertEquals("Test For Test",driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)"
                ,driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)"
                ,driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("color"));




    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        WebElement name = driver.findElement(By.name("name"));
        name.sendKeys("Adam");
//         click "Send"
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Adam, for your feedback!",driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)"
                ,driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.className("w3-blue")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!",driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)"
                ,driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        WebElement name = driver.findElement(By.name("name"));
        name.sendKeys("Adam");
        WebElement age = driver.findElement(By.name("age"));
        age.sendKeys("20");
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        WebElement comment = driver.findElement(By.name("comment"));
        comment.sendKeys("Test For Test ");

//         click "Send"
        driver.findElement(By.className("w3-blue")).click();
        Thread.sleep(1000);
//         click "No"
        driver.findElement(By.className("w3-red")).click();
        Thread.sleep(1000);
//         check fields are filled correctly
//        assertEquals("Adam",driver.findElement(By.id("name")).getText());
        assertEquals("Adam",driver.findElement(By.id("fb_name")).getAttribute("value"));
//        assertEquals("20",driver.findElement(By.id("age")).getText());
        assertEquals("20",driver.findElement(By.id("fb_age")).getAttribute("value"));
//        assertEquals("English",driver.findElement(By.id("language")).getText());
        assertTrue(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).isSelected());
//        assertEquals("male",driver.findElement(By.id("gender")).getText());
        assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).isSelected());
       assertEquals("Good",driver.findElement(By.id("like_us")).getAttribute("value"));
//        assertEquals("Test For Test",driver.findElement(By.id("comment")).getText());
        assertEquals("Test For Test",driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).getAttribute("value"));

    }
}
