package school.redrover.ui.component.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.PageUtils;
import school.redrover.ui.component.BaseComponent;
import school.redrover.ui.page.LoginPage;
import school.redrover.ui.page.UserStatusPage;

import java.util.function.Consumer;


public class UserAccountIconDropdown extends BaseComponent<UserAccountIconDropdown> {

    @FindBy(css = ".jenkins-dropdown__item:first-child")
    private WebElement userName;

    @FindBy(xpath = "//a[contains(., 'Account')]")
    private WebElement account;

    @FindBy(css = ".jenkins-dropdown__item:last-child")
    private WebElement signOut;


    public UserAccountIconDropdown(WebDriver driver) {
        super(driver);
    }

    @Override
    public UserAccountIconDropdown waitUntilComponentLoad() {
        getWait5().until(ExpectedConditions.elementToBeClickable(account));

        return this;
    }

    public UserStatusPage clickUserName() {
        PageUtils.clickJS(getDriver(), userName);

        return new UserStatusPage(getDriver()).waitUntilPageLoadJS();
    }

    public String getUserNameText() {
        return userName.getText();
    }

    public UserAccountIconDropdown getUserName(Consumer<String> stringConsumer) {
        stringConsumer.accept(getUserNameText());

        return this;
    }

    public LoginPage clickSignOut() {
        signOut.click();

        return new LoginPage(getDriver()).waitUntilPageLoadJS();
    }
}
