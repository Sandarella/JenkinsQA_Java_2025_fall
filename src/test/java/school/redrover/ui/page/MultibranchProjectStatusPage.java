package school.redrover.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.ui.component.project.status_page.sidebar.MultiBranchProjectSidebar;


public class MultibranchProjectStatusPage extends BaseProjectStatusPage<MultibranchProjectStatusPage, MultiBranchProjectSidebar> {

    @FindBy(id = "view-message")
    private WebElement description;

    @FindBy(css = "a[href$='/confirm-rename']")
    private WebElement sidebarRenameLink;

    @FindBy(id = "description-link")
    private WebElement addDescriptionLink;

    @FindBy(name = "description")
    private WebElement descriptionField;

    @FindBy(id = "disabled-message")
    private WebElement disabledMessage;

    @FindBy(xpath = "//span[text()='Delete Multibranch Pipeline']/ancestor::a")
    private WebElement deleteMenuItem;

    @FindBy(name = "Submit")
    private WebElement submitButton;


    public MultibranchProjectStatusPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiBranchProjectSidebar getSidebarComponent() {
        return new MultiBranchProjectSidebar(getDriver());
    }

    @Override
    public MultibranchProjectStatusPage getPage() {
        return this;
    }

    public String getDescription() {
        return description.getText();
    }

    public String getDisabledText() {
        return disabledMessage.getText();
    }

    public MultibranchProjectStatusPage clickAddDescriptionLink() {
        getWait2().until(ExpectedConditions.elementToBeClickable(addDescriptionLink)).click();

        return this;
    }

    public MultibranchProjectStatusPage sendDescription(String description) {
        descriptionField.clear();
        descriptionField.sendKeys(description);

        return this;
    }

    public String getDescriptionFieldText() {
        return descriptionField
                .getShadowRoot()
                .findElement(By.cssSelector("div"))
                .getText();
    }

    public boolean isAddDescriptionLinkEnabled() {
        return addDescriptionLink.isEnabled();
    }
}
