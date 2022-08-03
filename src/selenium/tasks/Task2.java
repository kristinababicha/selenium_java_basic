package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
    private void checkAllEmpty(){
        //name and age
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_age")).clear();
        //languages
        assertFalse(driver.findElements(By.className("w3-check")).get(0).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check")).get(1).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(2).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check")).get(3).isSelected());
        //Genre
        List< WebElement > elems = driver.findElements(By.cssSelector(".w3-radio"));
        assertFalse(elems.get(0).isSelected());
        assertFalse(elems.get(1).isSelected());
        assertTrue(elems.get(2).isSelected());
//        //how do you like
        List< WebElement > opns = driver.findElements(By.xpath("//*[@id=\"like_us\"]/option"));
        assertTrue(opns.get(0).isSelected());
        assertFalse(opns.get(1).isSelected());
        assertFalse(opns.get(2).isSelected());
        assertFalse(opns.get(3).isSelected());
        assertFalse(opns.get(4).isSelected());
        //comment
        driver.findElement(By.className("w3-border")).clear();

    }
    private void checkCss(String color, String bcolor, String ele){
        assertEquals(bcolor,
                driver.findElement(By.className(ele)).getCssValue("background-color"));
        assertEquals(color,
                driver.findElement(By.className(ele)).getCssValue("color"));
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         check that all field are empty and no ticks are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
       checkAllEmpty();
//         check that the button send is blue with white letters
       checkCss("rgba(255, 255, 255, 1)", "rgba(33, 150, 243, 1)", "w3-blue");
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         click "Send" without entering any data
        checkAllEmpty();
        driver.findElement(By.className("w3-blue")).click();
        Thread.sleep(4000);
//         check fields are empty or "null"
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        checkCss("rgba(255, 255, 255, 1)", "rgba(76, 175, 80, 1)","w3-green");
        checkCss("rgba(255, 255, 255, 1)", "rgba(244, 67, 54, 1)","w3-red");
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         fill the whole form, click "Send"
        //name and age
        String inName = "Lily";
        String inAge = "43";
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(inName);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(inAge);
        //languages
        driver.findElements(By.className("w3-check")).get(1).click();
        assertFalse(driver.findElements(By.className("w3-check")).get(0).isSelected());
        assertTrue(driver.findElements(By.cssSelector(".w3-check")).get(1).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(2).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check")).get(3).isSelected());
        //Genre
        List< WebElement > elems = driver.findElements(By.cssSelector(".w3-radio"));
        elems.get(1).click();
        assertFalse(elems.get(0).isSelected());
        assertTrue(elems.get(1).isSelected());
        assertFalse(elems.get(2).isSelected());
//        //how do you like
        List< WebElement > opns = driver.findElements(By.xpath("//*[@id=\"like_us\"]/option"));
        opns.get(2).click();
        assertFalse(opns.get(0).isSelected());
        assertFalse(opns.get(1).isSelected());
        assertTrue(opns.get(2).isSelected());
        assertFalse(opns.get(3).isSelected());
        assertFalse(opns.get(4).isSelected());
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).clear();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys(inName, inAge);
        driver.findElement(By.className("w3-blue")).click();
        Thread.sleep(4000);
//         check fields are filled correctly
        assertEquals("Lily", driver.findElement(By.id("name")).getText());
        assertEquals("43", driver.findElement(By.id("age")).getText());
        assertEquals("French", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals("Lily43", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        checkCss("rgba(255, 255, 255, 1)", "rgba(76, 175, 80, 1)","w3-green");
        checkCss("rgba(255, 255, 255, 1)", "rgba(244, 67, 54, 1)","w3-red");
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         enter only name
        checkAllEmpty();
        String inName = "Lily";
        driver.findElement(By.id("fb_name")).sendKeys(inName);
//         click "Send"
        driver.findElement(By.className("w3-blue")).click();
//         click "Yes"
        driver.findElement(By.cssSelector(".w3-green")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Lily, for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        checkCss("rgba(255, 255, 255, 1)", "rgba(76, 175, 80, 1)","w3-green");
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         click "Send" (without entering anything
        checkAllEmpty();
        driver.findElement(By.className("w3-blue")).click();
//         click "Yes"
        driver.findElement(By.cssSelector(".w3-green")).click();
//         check message text: "Thank you for your feedback!
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        checkCss("rgba(255, 255, 255, 1)", "rgba(76, 175, 80, 1)","w3-green");
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         fill the whole form
        //name and age
        String inName = "Lily";
        String inAge = "43";
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(inName);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(inAge);
        //languages
        driver.findElements(By.className("w3-check")).get(1).click();
        assertFalse(driver.findElements(By.className("w3-check")).get(0).isSelected());
        assertTrue(driver.findElements(By.cssSelector(".w3-check")).get(1).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(2).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check")).get(3).isSelected());
        //Genre
        List< WebElement > elems = driver.findElements(By.cssSelector(".w3-radio"));
        elems.get(1).click();
        assertFalse(elems.get(0).isSelected());
        assertTrue(elems.get(1).isSelected());
        assertFalse(elems.get(2).isSelected());
//        //how do you like
        List< WebElement > opns = driver.findElements(By.xpath("//*[@id=\"like_us\"]/option"));
        opns.get(2).click();
        assertFalse(opns.get(0).isSelected());
        assertFalse(opns.get(1).isSelected());
        assertTrue(opns.get(2).isSelected());
        assertFalse(opns.get(3).isSelected());
        assertFalse(opns.get(4).isSelected());
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).clear();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys(inName, inAge);
//         click "Send"
        driver.findElement(By.className("w3-blue")).click();
//         click "No"
        driver.findElement(By.cssSelector(".w3-red")).click();
//         check fields are filled correctly
        assertEquals("Lily", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("43", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertFalse(driver.findElements(By.className("w3-check")).get(0).isSelected());
        assertTrue(driver.findElements(By.cssSelector(".w3-check")).get(1).isSelected());
        assertFalse(driver.findElements(By.className("w3-check")).get(2).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check")).get(3).isSelected());
        assertTrue(elems.get(1).isSelected());
        assertTrue(opns.get(2).isSelected());
        assertEquals("Lily43", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).getAttribute("value"));

    }
}
