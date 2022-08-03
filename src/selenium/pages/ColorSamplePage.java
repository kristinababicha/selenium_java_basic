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
    private WebElement btn;
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loading;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement finish;

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        btn.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible
    public boolean btnVisible(){
        return btn.isDisplayed();
    }

    public boolean loadingVisible(boolean visible){
        if (loading.isDisplayed() == visible){
            return true;
        }else{
            return false;
        }
    }

    public boolean textVisible(boolean visible){
        if(finish.isDisplayed() == visible){
            return true;
        }else{
            return false;
        }
    }

    //Janis's version

}
