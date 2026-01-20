package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class FolderCreateViewPage extends BasePage<FolderCreateViewPage> {

    @FindBy(id = "name")
    private WebElement inputViewName;

    @FindBy(css = "[for='hudson.model.ProxyView']")
    private WebElement inputGlobalView;

    @FindBy(css = "[for='hudson.model.ListView']")
    private WebElement inputListView;

    @FindBy(css = "[for='hudson.model.MyView']")
    private WebElement inputMyView;


    public FolderCreateViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FolderCreateViewPage getPage() {
        return this;
    }

    public FolderCreateViewPage sendName(String name) {
        inputViewName.sendKeys(name);

        return this;
    }

    public FolderCreateViewPage selectTypeGlobalView() {
        inputGlobalView.click();

        return this.waitUntilPageLoadJS();
    }

    public FolderCreateViewPage selectTypeListView() {
        inputListView.click();

        return this.waitUntilPageLoadJS();
    }

    public FolderCreateViewPage selectTypeMyView() {
        inputMyView.click();

        return this.waitUntilPageLoadJS();
    }

    public void clickCreate() {
        getDriver().findElement(By.id("ok")).click();
    }
}
