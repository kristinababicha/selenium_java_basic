package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(how = How.CSS, using ="#start_green")
    private WebElement btnStartGreen;

    @FindBy(how = How.CSS, using ="#loading_green")
    private WebElement loadingGreen;

    @FindBy(how = How.CSS, using ="#finish_green")
    private WebElement finishText;

    public WebElement getLoadingGreen() {
        return loadingGreen;
    }

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        btnStartGreen.click();
    }

    public void checkStartLoadingGreenBtnNotVisible() {
        assertFalse(btnStartGreen.isDisplayed());
    }
    public void checkLoadingGreenIsVisible() {
        assertTrue(loadingGreen.isDisplayed());
    }
    public void checkLoadingGreenIsNOTVisible() {
        assertFalse(loadingGreen.isDisplayed());
    }
    public void checkGreenLoadedTxtIsVisible() {
        assertTrue(finishText.isDisplayed());
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible
}
