package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
    //         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(how = How.ID, using = "start_green")
    WebElement startGreen;

    @FindBy(how = How.ID, using = "loading_green")
    WebElement loadingGreen;

    @FindBy(how = How.ID, using = "finish_green")
    WebElement finishGreen;

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        startGreen.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible


    public void startLoadingGreenNotVisible() {
        assertFalse(startGreen.isDisplayed());

    }

//         * 2) check that text "Loading green..." is visible
    public void startLoadingGreenTextVisible() {
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());
    }

//         * 3) check that text "Loading green..." is not visible
    public void startLoadingGreenTextNotVisible() {
        assertFalse(loadingGreen.isDisplayed());
        assertEquals("", loadingGreen.getText());

    }

//         * 4) check that text "Green Loaded" is visible

    public void finishGreenVisible() {
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }


}
