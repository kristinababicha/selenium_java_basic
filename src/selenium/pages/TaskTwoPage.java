package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class TaskTwoPage extends GenericSamplePage {
    //         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(xpath = "//button[text()='Send']")
    WebElement sendButton;
    @FindBy(css = "button[class*=\"w3-btn w3-green\"]")
    WebElement yesButton;
    @FindBy(css = "button[class*=\"w3-btn w3-red\"]")
    WebElement noButton;
    @FindBy(className = "w3-check")
    List<WebElement> langCheckBox;
    @FindBy(className = "w3-radio")
    List<WebElement> genderRadioButton;
    @FindBy(id = "like_us") WebElement likeUsDropdown;
    @FindBy(id = "addPersonBtn") WebElement addPersonButton;
    @FindBy(xpath = "//button[@onclick = 'addPersonWithJobToList()']") WebElement addButton;

    public void clickSend() {
        sendButton.click();
    }

    public void clickYes() {
        yesButton.click();
    }

    public void clickNo() {
        noButton.click();
    }

    public void selectLanguage(int num) {
        langCheckBox.get(num).click();
    }

    public void selectGender(int num) {
        genderRadioButton.get(num).click();
    }

    public void selectValueFromLikeUsTable(String text) {
        Select select = new Select(likeUsDropdown);
        select.selectByValue(text);
    }
    public void clickAddPersonButton(){
        addPersonButton.click();
    }
    public void clickAddButton(){
        addButton.click();
    }
}