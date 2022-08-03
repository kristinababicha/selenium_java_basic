package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
@FindBy(id = "start_green") WebElement startGreen;
    @FindBy(id = "loading_green") WebElement loadingGreen;
    @FindBy(id = "finish_green") WebElement finishGreen;

    public void clickStartLoadingGreen() {
       startGreen.click();
    }
    //check that "Start loading green" button is not visible
    public void assertStartLoadingGreenNotVisible() {
        assertFalse(startGreen.isDisplayed());
    }

//   check that text "Loading green..." is visible
    public boolean isLoadingGreenVisible () {
        return loadingGreen.isDisplayed();
    }
    public void assertLoadingGreenIsVisible(){
        assertTrue(isLoadingGreenVisible());
        }
    public void assertFinishGreenNotVisible(){
        assertFalse(isLoadingGreenVisible());
    }
    public void assertFinishGreenIsVisible(){
        assertTrue(finishGreen.isDisplayed());
    }

//    check that text "Loading green..." is not visible
//    check that text "Green Loaded" is visible

}
