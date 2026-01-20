package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class FolderCredentialsPage extends BasePage<FolderCredentialsPage> {

    @FindBy(xpath = "//a[contains(text(), 'global')]")
    private  WebElement addCredentialsButton;

    @FindBy(xpath = "//a[@href = '/manage/credentials/store/system/domain/_/']")
    private  WebElement storesFromParentGlobal;


    public FolderCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FolderCredentialsPage getPage() {
        return this;
    }


    public String getCredentialsName(String name) {
        return getDriver().findElement(By.xpath("//a[contains(., '" + name + "')]")).getText();
    }

    public GlobalCredentialsPage clickGlobalLink() {

        new Actions(getDriver())
                .doubleClick(addCredentialsButton)
                .perform();

        return new GlobalCredentialsPage(getDriver()).waitUntilPageLoadJS();
    }
}
