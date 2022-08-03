package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
    //         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(id = "start_green")
     WebElement startButton;

    @FindBy(id = "loading_green")
    private WebElement loading;

    @FindBy(id = "finish_green")
     WebElement finish;


    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        startButton.click();
    }

    public void loadingNotvis() {
        assertFalse(startButton.isDisplayed());
    }

    public boolean loadingIsVis() {
        return loading.isDisplayed();
    }

    public void loadingIsVisAss(){
        assertTrue(loadingIsVis());
    }

    public void finish() {
        assertTrue(finish.isDisplayed());
    }
}





//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible



