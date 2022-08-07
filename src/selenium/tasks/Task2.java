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
        checkTextInputsAreEmpty();
        checkCheckboxInputsNotTicked();
//         "Don't know" is selected in "Genre"  -done
        checkRadioInputs();
//         "Choose your option" in "How do you like us?"
        Select select = new Select(driver.findElement(By.cssSelector(".w3-select")));
        assertEquals("Choose your option", select.getFirstSelectedOption().getText());
//         check that the button send is blue with white letters

        WebElement sendBtn = driver.findElement(By.cssSelector(".w3-btn-block"));
        String sendBtnColorBg = sendBtn.getCssValue("background-color");
        String sendBtnTxtColor = sendBtn.getCssValue("color");
        assertEquals("rgba(33, 150, 243, 1)", sendBtnColorBg);
        assertEquals("rgba(255, 255, 255, 1)", sendBtnTxtColor);
    }

    public void checkTextInputsAreEmpty() {
        driver.findElements(By.cssSelector(".w3-input"))
                .forEach(webElement -> {
                    assertEquals(0, webElement.getText().length());
                });
    }

    public void checkCheckboxInputsNotTicked() {
        driver.findElements(By.cssSelector(".w3-check"))
                .forEach(webElement -> {
                    assertFalse(webElement.isSelected());
                });
    }

    public void checkRadioInputs() {
        String selector = ".w3-radio[value=\"\"]";
        WebElement radio = driver.findElement(By.cssSelector(selector));
        boolean isChecked = Boolean.parseBoolean(radio.getAttribute("checked"));
        assertTrue(isChecked);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.cssSelector(".w3-btn-block")).click();
//         check fields are empty or "null"
        String selector = "p span";

        List<WebElement> elements = driver.findElements(By.cssSelector(selector));

        elements.forEach(webElement -> {
            boolean isEmptyOrNull = false;

            if (webElement.getText().length() == 0 || webElement.getText().equals("null")) {
                isEmptyOrNull = true;
            }

            assertTrue(isEmptyOrNull);
        });
//         check button colors (green with white letter and red with white letters)
        verifyFormBtnsColorCheck();

    }

/////////////////////////////////////////////////////////////////////////////////////////////

    @Test
public void notEmptyFeedbackPage() throws Exception {
        // TODO:
//       fill the whole form
//       click "Send"
//       check fields are filled correctly
//       check button colors (green with white letter and red with white letters)

        testDataForFeedbackForm();
        driver.findElement(By.cssSelector(".w3-btn-block")).click();
        Thread.sleep(2000);
        verifyFormBtnsColorCheck();


    }


/////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//      TODO:
        WebElement inputName= driver.findElement(By.cssSelector("#fb_name"));
        WebElement sendFeedbackBtn = driver.findElement(By.cssSelector(".w3-btn-block"));
        String feedbackName = "Imya";
//         enter only name
        inputName.sendKeys(feedbackName);
//         click "Send"
        sendFeedbackBtn.click();
//         click "Yes"
        driver.findElement(By.cssSelector(".w3-btn.w3-green")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, "+ feedbackName+ ", for your feedback!",driver.findElement(By.cssSelector("#message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.cssSelector(".w3-panel")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.cssSelector(".w3-panel")).getCssValue("color"));
        }

   @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
       checkTextInputsAreEmpty();
       checkCheckboxInputsNotTicked();
       checkRadioInputs();
       Thread.sleep(1500);
       driver.findElement(By.cssSelector(".w3-btn-block")).click();
//         click "Yes"
       driver.findElement(By.cssSelector(".w3-btn.w3-green")).click();
//         check message text: "Thank you for your feedback!"
       assertEquals("Thank you for your feedback!", driver.findElement(By.cssSelector("#message")).getText());
//         color of text is white with green on the background
       assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.cssSelector(".w3-panel")).getCssValue("background-color"));
       assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.cssSelector(".w3-panel")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        testDataForFeedbackForm();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".w3-btn-block")).click();
        driver.findElement(By.cssSelector(".w3-btn.w3-red")).click();

    }




    public void verifyFormBtnsColorCheck() {
        WebElement btnGreen = driver.findElement(By.cssSelector(".w3-btn.w3-green"));
        String btnBgColor = btnGreen.getCssValue("background-color");
        String btnTxtColor = btnGreen.getCssValue("color");
        assertEquals("rgba(76, 175, 80, 1)", btnBgColor);
        assertEquals("rgba(255, 255, 255, 1)", btnTxtColor);

        WebElement btnRed = driver.findElement(By.cssSelector(".w3-btn.w3-red"));
        String btnRedBg = btnRed.getCssValue("background-color");
        String btnRedTxtColor = btnRed.getCssValue("color");
        assertEquals("rgba(244, 67, 54, 1)", btnRedBg);
        assertEquals("rgba(255, 255, 255, 1)", btnRedTxtColor);
    }

    public void testDataForFeedbackForm() {

        String testName = "Name";
        String testAge = "42";
        String testLanguage = "Spanish";
        String testGender = "female";
        String testFeedbackValue = "Good";
        String testComment ="All Good";
        String[] testValues = {testName, testAge, testLanguage, testGender, testFeedbackValue, testComment};

        driver.findElement(By.cssSelector("#fb_name")).sendKeys(testName);
        driver.findElement(By.cssSelector("#fb_age")).sendKeys(testAge);
        driver.findElement(By.cssSelector(".w3-check[value=\"" + testLanguage + "\"]")).click();
        driver.findElement(By.cssSelector(".w3-radio[value=\"" + testGender + "\"]")).click();
        driver.findElement(By.cssSelector("#like_us [value=\"" + testFeedbackValue + "\"]")).click();
        driver.findElement(By.cssSelector("textarea[name=\"comment\"]")).sendKeys(testComment);

        String resultsSelector = "p span";
        List<WebElement> elements = driver.findElements(By.cssSelector(resultsSelector));
        for (int i=0; i< elements.size(); i++) {
            System.out.println("i: " + i);
            WebElement currentElement = elements.get(i);
            assertEquals(testValues[i], currentElement.getText());
        }


    }
}