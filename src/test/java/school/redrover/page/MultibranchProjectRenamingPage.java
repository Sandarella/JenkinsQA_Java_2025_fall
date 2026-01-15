package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class MultibranchProjectRenamingPage extends BasePage<MultibranchProjectRenamingPage> {

    @FindBy(name = "newName")
    private WebElement renameField;


    public MultibranchProjectRenamingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultibranchProjectRenamingPage getPage() {
        return this;
    }

    @Override
    public MultibranchProjectRenamingPage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.visibilityOf(renameField));

        return this;
    }

    public MultibranchProjectRenamingPage renameJob(String jobName) {
        renameField.clear();
        renameField.sendKeys(jobName);

        return this;
    }

    public ErrorPage submitForm() {
        getDriver().findElement(By.tagName("form")).submit();

        return new ErrorPage(getDriver());
    }

    public MultibranchProjectStatusPage renameMultibranchPipeline(String jobName) {
        renameField.clear();
        renameField.sendKeys(jobName + Keys.ENTER);

        return new MultibranchProjectStatusPage(getDriver()).waitUntilPageLoadJS();
    }
}
