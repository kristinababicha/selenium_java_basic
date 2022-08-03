package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(id = "start_green") WebElement startGreen;
    @FindBy(id = "loading_green") private WebElement loadingGreen;
    @FindBy(id = "finish_green") WebElement finishGreen;

    public void clickStartLoadingGreen() {startGreen.click();}
//         TODO:
//         implement clicking on "Start loading green" button


    public void assertStartLoadingGreenNotVisible() {assertFalse(startGreen.isDisplayed());
    }

    public boolean isLoadingGreenVisible() {return loadingGreen.isDisplayed();
    }

    public void assertLoadingGreenIsVisible() { assertTrue(isLoadingGreenVisible());
    }

    public void assertLoadingGreenNotVisible() {assertFalse(isLoadingGreenVisible());
    }
    public void assertFinishGreenIsVisible(){assertTrue(finishGreen.isDisplayed());
    }
//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible

//         * 2) check that text "Loading green..." is visible

//         * 3) check that text "Loading green..." is not visible

//         * 4) check that text "Green Loaded" is visible
}
