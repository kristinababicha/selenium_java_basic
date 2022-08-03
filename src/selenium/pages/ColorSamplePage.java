package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:
    @FindBy(id = "start_green")
        private WebElement startGreenButton;

    @FindBy(id = "loading_green")
        private WebElement loadingGreen;

    @FindBy(id = "finish_green")
        private WebElement finishGreen;

//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        startGreenButton.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
    public void startGreenButtonIsNotVisible(){
        assertFalse(startGreenButton.isDisplayed());
    }

//         * 2) check that text "Loading green..." is visible
    public void loadingGreenIsVisible(){
        assertTrue(loadingGreen.isDisplayed());
    }
//         * 3) check that text "Loading green..." is not visible
    public void loadingGreenIsNotVisible(){
        assertFalse(loadingGreen.isDisplayed());
    }
//         * 4) check that text "Green Loaded" is visible
    public void greenLoadedIsVisible(){
        assertTrue(finishGreen.isDisplayed());
    }
}
