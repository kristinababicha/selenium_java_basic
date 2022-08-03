package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
        @FindBy(id = "start_green") WebElement startIt;
        @FindBy(id = "loading_green") WebElement loadIt;
        @FindBy(id = "finish_green") WebElement finishIt;
    public void clickStartLoadingGreen() {
//         implement clicking on "Start loading green" button
        startIt.click();
    }
//         * 1) check that "Start loading green" button is not visible
    public void startLoadNotVisible(){
        assertFalse(startIt.isDisplayed());
    }
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
    public boolean loadingVisible(){
        return loadIt.isDisplayed();
    }
//         * 4) check that text "Green Loaded" is visible
    public void finishVisible(){
        assertTrue(finishIt.isDisplayed());
    }
}
