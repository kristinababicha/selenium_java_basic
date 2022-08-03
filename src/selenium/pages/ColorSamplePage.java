package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(id = "start_green") WebElement start_green;
    @FindBy(id = "loading_green") WebElement loading_green;
    @FindBy(id = "finish_green") WebElement finish_green;


    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        start_green.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
    public void asserStartLoadingGreenNotVisible() {
        assertFalse(start_green.isDisplayed());

    }
//         * 2) check that text "Loading green..." is visible
    public boolean isLoadingGreenVisible() {
        return loading_green.isDisplayed();

    }
//         * 3) check that text "Loading green..." is not visible

    public void asserLoadingGreenNotVisible(){
        assertFalse(isLoadingGreenVisible());

    }
//
//         * 4) check that text "Green Loaded" is visible
    public void assertFinishGreenIsVisible(){
        assertTrue(finish_green.isDisplayed());

    }



    }

