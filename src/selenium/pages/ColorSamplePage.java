package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html

//implemented elements to use them in methods
    @FindBy(id = "start_green") WebElement startGreen;
    @FindBy(id = "loading_green") WebElement loadingGreen;
    @FindBy(id = "finish_green") WebElement finishGreen;

    //implement clicking on "Start loading green" button
    public void clickStartLoadingGreen() {
        startGreen.click();
    }

///        TODO:



       // * 1) check that "Start loading green" button is not visible
//         TODO
       //         * 2) check that text "Loading green..." is visible
    public void startLoadingGreenNotVisible(){
        assertFalse(startGreen.isDisplayed());

    }
//* 3) check that text "Loading green..." is not visible (can be implemented as one)
    public boolean loadingGreenVisible() {
        return loadingGreen.isDisplayed();
    }
    //         * 4) check that text "Green Loaded" is visible
    public void finishGreenVisible(){
        assertTrue(finishGreen.isDisplayed());
    }

    }



