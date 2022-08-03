package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//          Define elements in page
//          see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(id = "start_green") WebElement start_green_button;
    @FindBy(id = "loading_green") WebElement loading_green;
    @FindBy(id = "finish_green") WebElement finish_green;

    public void clickStartLoadingGreen() {
//         TODO:
//          implement clicking on "Start loading green" button
        start_green_button.click();
    }

//         TODO
//          Implement methods for:
//          * 1) check that "Start loading green" button is not visible
//          * 2) check that text "Loading green..." is visible
//          * 3) check that text "Loading green..." is not visible
//          * 4) check that text "Green Loaded" is visible
    public void checkStartLoadingButtonNotVisible() {
        assertFalse(start_green_button.isDisplayed());
    }

    public void checkLoadingGreenIsVisible() {
        assertTrue(loading_green.isDisplayed());
    }

    public void checkLoadingGreenNotVisible() {
        assertFalse(loading_green.isDisplayed());
    }

    public void checkGreenLoadedIsVisible() {
        assertTrue(finish_green.isDisplayed());
    }

}
