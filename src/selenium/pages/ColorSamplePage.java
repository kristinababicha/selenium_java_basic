package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
            @FindBy(id = "start_green") WebElement startgreen;
            @FindBy(id = "loading_green") private WebElement loadinggreen;
            @FindBy(id = "finish_green") WebElement finishgreen;

    public void clickStartLoadingGreen() {
        startgreen.click();
    }
    public void assertStartLoadingGreenVisible() {
        assertFalse(startgreen.isDisplayed());
    }

    public boolean isLoadingGreenVisible() {
        return loadinggreen.isDisplayed();
    }

    public void assertLoadingGreenIsVisible() {
        assertTrue(isLoadingGreenVisible());
    }
    public void assertLoadingGreenIsNotVisible() {
        assertFalse(isLoadingGreenVisible());
    }

    public void assertfinishGreenIsVisible(){
    assertTrue(finishgreen.isDisplayed());


}

//         TODO:
//         implement clicking on "Start loading green" button


    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible

