package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        // System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
        //check that all field are empty and no ticks are clicked
        WebElement userName = driver.findElement(By.id("fb_name"));
        assertEquals("", userName.getText());

        WebElement userAge = driver.findElement(By.id("fb_age"));
        assertEquals("", userAge.getText());

        //for checkBox
        List<WebElement> checkBox = driver.findElements(By.xpath("//input[@class='w3-check']"));
        for (WebElement element : checkBox) {
            assertFalse(element.isSelected());
        }

        //for dropDown

        WebElement submitButton = driver.findElement(By.id("like_us"));
        String submitButtonText = submitButton.findElement(By.tagName("option")).getText();
        String buttonText = "Choose your option";

        assertEquals(buttonText, submitButtonText);



        //for radioButton

        WebElement maleButton = driver.findElements(By.className("w3-validate")).get(0);
        assertFalse(maleButton.isSelected());

        WebElement femaleButton = driver.findElements(By.className("w3-validate")).get(1);
        assertFalse(femaleButton.isSelected());


//         "Don't know" is selected in "Genre"


        WebElement DontKnowButton = driver.findElements(By.className("w3-radio")).get(2);
        assertTrue(DontKnowButton.isSelected());




//         "Choose your option" in "How do you like us?"

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Ok, i guess");

//         check that the button send is blue with white letters

        String colorCheck = driver.findElement(By.xpath("//button[@type='submit']")).getCssValue("background-color");
        assertEquals("rgba(33, 150, 243, 1)", colorCheck);


    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        WebElement emptyEnter = driver.findElement(By.xpath("//button[@type='submit']"));
        emptyEnter.click();

//         check fields are empty or "null"

        List<WebElement> allFields = driver.findElements(By.className("description"));


        int formItemCount = 0;

        for (int i = 0; i < allFields.size(); i++) {
            String herBirItem = allFields.get(i).findElement(By.tagName("span")).getText();
            if (Objects.equals(herBirItem, "null") || herBirItem.isEmpty()) {
                formItemCount += 1;
            }
        }
        assertEquals(formItemCount, 6);


        // }
//         check button colors
//         (green with white letter and red with white letters)

        String colorCheckYes = driver.findElement(By.xpath("//div/button[@onclick='openFeedback()']")).getCssValue("background-color");

        assertEquals("rgba(76, 175, 80, 1)", colorCheckYes);

        String colorCheckNo = driver.findElement(By.xpath("//div/button[@onclick='window.history.back();']")).getCssValue("background-color");

        assertEquals("rgba(244, 67, 54, 1)", colorCheckNo);
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        String name = "Gizem";
        String age = "28";
        String comment = "comment comment comment";


        WebElement userName = driver.findElement(By.id("fb_name"));
        userName.sendKeys(name);


        WebElement userAge = driver.findElement(By.id("fb_age"));
        userAge.sendKeys(age);

        WebElement languages = driver.findElements(By.className("w3-check")).get(0);
        languages.click();

        WebElement gender = driver.findElements(By.className("w3-radio")).get(1);
        gender.click();

        Select dropdownLikeUs = new Select(driver.findElement(By.className("w3-select")));
        dropdownLikeUs.selectByVisibleText("Ok, i guess");

        WebElement commentArea = driver.findElement(By.tagName("textarea"));
        commentArea.sendKeys(comment);


        WebElement submitButtonClick = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButtonClick.click();


//         check fields are filled correctly
        List<WebElement> allFields = driver.findElements(By.tagName("span"));
        String formName = allFields.get(0).getText();
        String formAge = allFields.get(1).getText();
        String formLanguage = allFields.get(2).getText();
        String formGenre = allFields.get(3).getText();
        String formOption = allFields.get(4).getText();
        String formComment = allFields.get(5).getText();

        assertEquals(formName,name);
        assertEquals(formAge,age);
        assertEquals(formLanguage,"English");
        assertEquals(formGenre,"female");
        assertEquals(formOption,"Ok, i guess");
        assertEquals(formComment,comment);


//         check button colors
//         (green with white letter and red with white letters)

        String colorCheckYes = driver.findElement(By.xpath("//div/button[@onclick='openFeedback()']")).getCssValue("background-color");

        assertEquals("rgba(76, 175, 80, 1)", colorCheckYes);

        String colorCheckNo = driver.findElement(By.xpath("//div/button[@onclick='window.history.back();']")).getCssValue("background-color");

        assertEquals("rgba(244, 67, 54, 1)", colorCheckNo);
    }


    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name

        String name = "Gizem";

        WebElement userName = driver.findElement(By.id("fb_name"));
        userName.sendKeys(name);

//         click "Send"

        WebElement clickSend = driver.findElement(By.xpath("//button[@type='submit']"));
        clickSend.click();


//         click "Yes"

        WebElement clickYes = driver.findElement(By.xpath("//div/button[@onclick='openFeedback()']"));
        clickYes.click();

//         check message text: "Thank you, NAME, for your feedback!"

        String message = driver.findElement(By.id("message")).getText();
        assertEquals("Thank you, Gizem, for your feedback!", message);


//         color of text is white with green on the background

        String textBackGround = driver.findElement(By.xpath("//div[@class='w3-panel w3-green']")).getCssValue("background-color");
        String textColor = driver.findElement(By.xpath("//div[@class='w3-panel w3-green']")).getCssValue("color");

        assertEquals("rgba(76, 175, 80, 1)", textBackGround);
        assertEquals("rgba(255, 255, 255, 1)", textColor);


    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything

        WebElement clickWithOut = driver.findElement(By.xpath("//button[@type='submit']"));
        clickWithOut.click();
//         click "Yes"
        WebElement clickYesWithOut = driver.findElement(By.xpath("//div/button[@onclick='openFeedback()']"));
        clickYesWithOut.click();

//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        String message = driver.findElement(By.id("message")).getText();
        assertEquals("Thank you for your feedback!", message);


        String textBackGround = driver.findElement(By.xpath("//div[@class='w3-panel w3-green']")).getCssValue("background-color");
        String textColor = driver.findElement(By.xpath("//div[@class='w3-panel w3-green']")).getCssValue("color");

        assertEquals("rgba(76, 175, 80, 1)", textBackGround);
        assertEquals("rgba(255, 255, 255, 1)", textColor);



    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form

        String nameEnter = "mike";
        String ageEnter = "30";
        String commentEnter = "comment comment";


        WebElement userName = driver.findElement(By.id("fb_name"));
        userName.sendKeys(nameEnter);


        WebElement userAge = driver.findElement(By.id("fb_age"));
        userAge.sendKeys(ageEnter);

        WebElement languages = driver.findElements(By.className("w3-check")).get(0);
        languages.click();

        WebElement gender = driver.findElements(By.className("w3-radio")).get(1);
        gender.click();

        Select dropdownLikeUs = new Select(driver.findElement(By.className("w3-select")));
        dropdownLikeUs.selectByVisibleText("Ok, i guess");

        WebElement commentArea = driver.findElement(By.tagName("textarea"));
        commentArea.sendKeys(commentEnter);

//         click "Send"

        WebElement submitButtonClick = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButtonClick.click();


 //       check fields are filled correctly

        List<WebElement> allFields = driver.findElements(By.tagName("span"));
        String formName = allFields.get(0).getText();
        String formAge = allFields.get(1).getText();
        String formLanguage = allFields.get(2).getText();
        String formGenre = allFields.get(3).getText();
        String formOption = allFields.get(4).getText();
        String formComment = allFields.get(5).getText();

        assertEquals(formName,nameEnter);
        assertEquals(formAge,ageEnter);
        assertEquals(formLanguage,"English");
        assertEquals(formGenre,"female");
        assertEquals(formOption,"Ok, i guess");
        assertEquals(formComment,commentEnter);


//         click "No"


        WebElement clickCheckNo = driver.findElement(By.xpath("//div/button[@onclick='window.history.back();']"));
        clickCheckNo.click();



    }
}
