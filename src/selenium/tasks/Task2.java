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
        assertTrue(driver.findElements(By.className("w3-input w3-border")).isEmpty());

        List<WebElement> elems = driver.findElements(By.xpath("//*[@type=\"radio\"]"));
        assertFalse(elems.get(0).isSelected());
        assertFalse(elems.get(1).isSelected());


        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        assertFalse(checkBoxes.get(0).isSelected());
        assertFalse(checkBoxes.get(1).isSelected());
        assertFalse(checkBoxes.get(2).isSelected());
        assertFalse(checkBoxes.get(3).isSelected());

//         "Don't know" is selected in "Genre"
        assertTrue(elems.get(2).isSelected());
//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Choose your option");
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)",
                driver.findElement(By.className("w3-btn-block")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-btn-block")).getCssValue("color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.className("w3-btn-block")).click();
//         check fields are empty or "null"
        assertTrue(driver.findElement(By.id("name")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("age")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("language")).getText().isEmpty());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertTrue(driver.findElement(By.id("comment")).getText().isEmpty());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("color"));



    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("James");
        driver.findElement(By.id("fb_age")).sendKeys("48");
        driver.findElement(By.xpath("//*[@value=\"Chinese\"]")).click();
        driver.findElement(By.xpath("//*[@value=\"male\"]")).click();
        driver.findElement(By.className("w3-btn-block")).click();

//         check fields are filled correctly
        assertEquals("James", driver.findElement(By.id("name")).getText());
        assertEquals("48", driver.findElement(By.id("age")).getText());
        assertEquals("Chinese", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertTrue(driver.findElement(By.id("comment")).getText().isEmpty());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        String name = "Jim";
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(name);
//         click "Send"
        driver.findElement(By.className("w3-blue")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals(("Thank you, " + name + ", for your feedback!"), driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.id("message")).getCssValue("color"));

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.className("w3-btn-block")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you for your feedback!"
        driver.findElements(By.id("message"));
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.className("w3-green")).getCssValue("color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("James");
        driver.findElement(By.id("fb_age")).sendKeys("48");
        driver.findElement(By.xpath("//*[@value=\"Chinese\"]")).click();
        driver.findElement(By.xpath("//*[@value=\"male\"]")).click();
        //driver.findElement(By.id("like_us")).click();
        //driver.findElement(By.xpath("//*[@idvalue=\"option[3]\"]")).click();
        ////*[@id="like_us"]/option[3]
        //*[@id="like_us"]/option[3]
        //*[@id="like_us"]/option[5]
        driver.findElement(By.id("like_us")).click();
        driver.findElement(By.xpath("//option[@value='Bad']")).click();
        driver.findElement(By.name("comment")).sendKeys("Nice webpage!");

//         click "Send"
        driver.findElement(By.className("w3-btn-block")).click();
//         click "No"
        driver.findElement(By.className("w3-red")).click();

//         check fields are filled correctly
        assertEquals("James", driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value"));
        assertEquals("48", driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value"));
        assertTrue(driver.findElement(By.xpath("//input[@value='Chinese']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//input[@value='male']")).isSelected());
        assertEquals("Bad", driver.findElement(By.id("like_us")).getAttribute("value"));
        assertEquals("Nice webpage!", driver.findElement(By.xpath("//textarea[@name='comment']")).getAttribute("value"));


    }
}
