package school.redrover.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class MultiConfigProjectRenamingPage extends BasePage<MultiConfigProjectRenamingPage> {

    @FindBy(name = "newName")
    private WebElement nameField;


    public MultiConfigProjectRenamingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigProjectRenamingPage getPage() {
        return this;
    }

    @Override
    public MultiConfigProjectRenamingPage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.visibilityOf(nameField));

        return this;
    }

    public MultiConfigProjectRenamingPage clearNameField() {
        nameField.clear();

        return this;
    }

    public MultiConfigProjectStatusPage sendNewProjectName(String jobName) {
        nameField.sendKeys(jobName + Keys.ENTER);

        return new MultiConfigProjectStatusPage(getDriver()).waitUntilPageLoadJS();
    }

}
