package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {

    //         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(how = How.ID, using = "start_green")
    private WebElement loadGreenButton;
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadGreenMessage;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement finGreenMessage;

    public void clickStartLoadingGreen() {

//         implement clicking on "Start loading green" button
        loadGreenButton.click();
    }

    //         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
    public void checkGreenButtonIsNotVisible() {
        assertFalse(loadGreenButton.isDisplayed());
    }

    //         * 2) check that text "Loading green..." is visible
    public void checkLoadGreenIsVisible() {
        assertTrue(loadGreenMessage.isDisplayed());
    }

    //         * 3) check that text "Loading green..." is not visible
    public void checkLoadGreenIsNotVisible() {
        assertFalse(loadGreenMessage.isDisplayed());
    }
//         * 4) check that text "Green Loaded" is visible
    public void checkLoadedGreenIsVisible(){
        assertTrue(finGreenMessage.isDisplayed());
    }
}
