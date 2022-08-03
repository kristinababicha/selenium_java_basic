package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:

    //         Define elements in page
    @FindBy(how = How.ID, using = "start_green")
    WebElement startButton;
    @FindBy(how = How.ID, using = "loading_green")
    WebElement loadingGreen;
    @FindBy(how = How.ID, using = "finish_green")
    WebElement finishButton;


//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html

    // implement clicking on "Start loading green" button
    public void clickStartLoadingGreen() {
        startButton.click();
    }

    //         TODO
//         Implement methods for:
//      * 1) check that "Start loading green" button is not visible
    public void assertStartLoadingGreenNotVisible() {
        assertFalse(startButton.isDisplayed());
    }

    //         * 2) check that text "Loading green..." is visible
    public boolean LoadingGreenIsVisible() {
        return loadingGreen.isDisplayed();
    }

    // * 3) check that text "Loading green..." is not visible
    public void assertLoadingGreenIsVisible() {
        assertTrue(LoadingGreenIsVisible());
    }

    public void assertGreenLoadingIsVisible() {
        assertTrue(LoadingGreenIsVisible());
    }


    //   * 4) check that text "Green Loaded" is visible
    public void assertFinishGreenIsVisible() {
        assertTrue(finishButton.isDisplayed());
    }
}

