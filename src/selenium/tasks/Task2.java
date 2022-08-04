package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;
    WebElement name;
    WebElement age;
    List<WebElement> checkBoxes;
    List<WebElement> radioButton;
    WebElement dropDown;
    WebElement commentBox;
    Select select;
    WebElement send;


    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");

         name=driver.findElement(By.id("fb_name"));
         age=driver.findElement(By.id("fb_age"));
         checkBoxes =driver.findElements(By.xpath("//input[@type='checkbox']"));
         radioButton =driver.findElements(By.xpath("//input[@type='radio']"));
         dropDown=driver.findElement(By.xpath("//select[@id='like_us']"));commentBox=driver.findElement(By.xpath("//textarea[@name='comment']"));
         select = new Select(dropDown);
         send= driver.findElement(By.xpath("//button[@type='submit']"));
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no ticks are clicked



        assertEquals("",name.getText());
        assertEquals("", age.getText());

        for(int i=0;i<checkBoxes.size();i++){
            assertFalse(checkBoxes.get(i).isSelected());
        }
        for(int j=0;j< radioButton.size()-1;j++){
            assertFalse(radioButton.get(j).isSelected());
        }
        assertEquals("Choose your option",select.getFirstSelectedOption().getText());
        assertEquals("",commentBox.getText());

//         "Don't know" is selected in "Genre"
        assertTrue(radioButton.get(2).isSelected());
//         "Choose your option" in "How do you like us?"
        select.selectByValue("Good");
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)",send.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",send.getCssValue("color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
        WebElement send= driver.findElement(By.xpath("//button[@type='submit']"));
//         TODO:
//         click "Send" without entering any data
            send.click();

//         check fields are empty or "null"
        assertEquals("",driver.findElement(By.xpath("//span[@id='name']")));
        assertEquals("",driver.findElement(By.xpath("//span[@id='age']")));
        assertEquals("",driver.findElement(By.xpath("//span[@id='language']")));
        assertEquals("",driver.findElement(By.xpath("//span[@id='gender']")));
        assertEquals("",driver.findElement(By.xpath("//span[@id='option']")));
        assertEquals("",driver.findElement(By.xpath("//span[@id='comment']")));

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noButton=driver.findElement(By.xpath("//button[text()='No']"));

        assertEquals("rgba(76, 175, 80, 1)",yesButton.getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)",noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",yesButton.getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)",noButton.getCssValue("color"));

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        String nameVar = "vignesh";
        String ageVar = "31";
        String selectBoxValue = "Good";
        String commentBoxValue = "Test";

        name.sendKeys(nameVar);
        age.sendKeys(ageVar);
        checkBoxes.get(0).click();
        radioButton.get(0).click();
        select.selectByValue(selectBoxValue);
        commentBox.sendKeys(commentBoxValue);
        send.click();

//         check fields are filled correctly

        assertEquals(nameVar,driver.findElement(By.xpath("//span[@id='name']")).getText());
        assertEquals(ageVar,driver.findElement(By.xpath("//span[@id='age']")).getText());
        assertEquals("English",driver.findElement(By.xpath("//span[@id='language']")).getText());
        assertEquals("male",driver.findElement(By.xpath("//span[@id='gender']")).getText());
        assertEquals(selectBoxValue,driver.findElement(By.xpath("//span[@id='option']")).getText());
        assertEquals(commentBoxValue,driver.findElement(By.xpath("//span[@id='comment']")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noButton=driver.findElement(By.xpath("//button[text()='No']"));

        assertEquals("rgba(76, 175, 80, 1)",yesButton.getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)",noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",yesButton.getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)",noButton.getCssValue("color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
//         TODO:
//         enter only name
        String nameval = "Shivani";
        name.sendKeys(nameval);
//         click "Send"
        send.click();
//         click "Yes"
            yesButton.click();
//         check message text: "Thank you, NAME, for your feedback!"
            WebElement thankYouMsg=driver.findElement(By.xpath("//h2[@id='message']"));
            assertEquals("Thank you, Vignesh, for your feedback!",thankYouMsg.getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)",thankYouMsg.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",thankYouMsg.getCssValue("background-color"));

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        send.click();
//         click "Yes"
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        yesButton.click();
//         check message text: "Thank you for your feedback!"
        WebElement thankYouMsg=driver.findElement(By.xpath("//h2[@id='message']"));
        assertEquals("Thank you for your feedback!",thankYouMsg.getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)",thankYouMsg.getCssValue("color"));
        assertEquals("rgba(76,175,80,1)",thankYouMsg.getCssValue("background-color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {


//         TODO:WebElement noButton = driver.findElement(By.xpath("//button[text()='No']"));
//         fill the whole form
        String nameVar = "vignesh";
        String ageVar = "31";
        String selectBoxValue = "Good";
        String commentBoxValue = "Test";

        name.sendKeys(nameVar);
        age.sendKeys(ageVar);
        checkBoxes.get(0).click();
        radioButton.get(0).click();
        select.selectByValue(selectBoxValue);
        commentBox.sendKeys(commentBoxValue);

//         click "Send"
        send.click();
        WebElement noButton=driver.findElement(By.xpath("//button[text()='No']"));


        assertEquals(nameVar,driver.findElement(By.xpath("//span[@id='name']")).getText());
        assertEquals(ageVar,driver.findElement(By.xpath("//span[@id='age']")).getText());
        assertEquals("English",driver.findElement(By.xpath("//span[@id='language']")).getText());
        assertEquals("male",driver.findElement(By.xpath("//span[@id='gender']")).getText());
        assertEquals(commentBoxValue,driver.findElement(By.xpath("//span[@id='comment']")).getText());
//         click "No"
        noButton.click();
//         check fields are filled correctly




    }
}
