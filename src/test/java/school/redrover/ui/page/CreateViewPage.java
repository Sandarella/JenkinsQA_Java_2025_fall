package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

import java.util.List;


public class CreateViewPage extends BasePage<CreateViewPage> {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(xpath = "//label[text() = 'My View']")
    private WebElement clickMyView;

    @FindBy(id = "ok")
    private WebElement createButton;

    @FindBy(xpath = "//div[@class='jenkins-radio']//label")
    private List<WebElement> typeViewList;

    @FindBy(xpath = "//label[text() = 'List View']")
    private WebElement listViewRadioButton;

    public CreateViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CreateViewPage getPage() {
        return this;
    }

    public CreateViewPage sendViewName(String name){
        nameField.sendKeys(name);

        return this;
    }

    public CreateViewPage clickMyViewTypeRadioButton(){
        clickMyView.click();

        return this;
    }

    public HomePage clickCreateButtonForNewView() {
        createButton.click();

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public List<String> getTypeViewList(){
        return typeViewList
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public EditViewPage selectListViewRadioAndCreate(){
        listViewRadioButton.click();
        createButton.click();

        return new EditViewPage(getDriver()).waitUntilPageLoadJS();
    }
}
