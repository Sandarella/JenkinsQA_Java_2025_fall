package school.redrover.component.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.PageUtils;
import school.redrover.component.BaseComponent;
import school.redrover.page.FreestyleProjectStatusPage;
import school.redrover.page.UserStatusPage;

import java.util.ArrayList;
import java.util.List;


public class SearchComponent extends BaseComponent<SearchComponent> {

    @FindBy(id = "command-bar")
    private WebElement searchField;

    @FindBy(xpath = "//div[@id='search-results']//a")
    private List<WebElement> searchResults;

    @FindBy(css = ".jenkins-command-palette__results__item--hover svg[aria-hidden='true']")
    private WebElement svgSearchResult;

    @FindBy(xpath = "//span[text()='No results for']")
    private WebElement noSearchResult;


    public SearchComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchComponent waitUntilComponentLoad() {
        getWait5().until(ExpectedConditions.elementToBeClickable(searchField));

        return this;
    }

    public SearchComponent waitUntilComponentLoadResult() {
        getWait5().until(ExpectedConditions.elementToBeClickable(svgSearchResult));

        return this;
    }

    public SearchComponent waitUntilComponentLoadNoResult() {
        getWait5().until(ExpectedConditions.visibilityOf(noSearchResult));

        return this;
    }

    public SearchComponent searchFor(String jobName) {
        searchField.sendKeys(jobName);

        try {
            return this.waitUntilComponentLoadResult();
        } catch (TimeoutException e) {
            return this.waitUntilComponentLoadNoResult();
        } catch (Throwable e) {
            return this.waitUntilComponentLoad();
        }
    }

    public SearchComponent searchFor(String jobName, String previousItemName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(searchField)).clear();
        searchField .sendKeys(jobName);

        if (previousItemName != null && !previousItemName.isEmpty()) {
            getWait5().until(driver ->
                    !getWait5().until(ExpectedConditions.visibilityOfAllElements(searchResults)).get(0).getText().contains(previousItemName));
        }

        return this;
    }

    public boolean isNoResultsFound(String jobName) {
        try {
            getWait5().until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("search-results"), "No results for %s".formatted(jobName)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<String> getSearchResultsAndClose() {
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.className("jenkins-command-palette__results__heading")));
        List<String> searchResultsTexts = new ArrayList<>();

        for (WebElement element : searchResults) {
            searchResultsTexts.add(element.getText());
        }

        new Actions(getDriver())
                .moveToElement((searchField), 0, -50)
                .click()
                .perform();

        return searchResultsTexts;
    }

    public UserStatusPage searchForUser(String userName) {
        searchField.sendKeys(userName);

        this.waitUntilComponentLoadResult()
                .searchField
                .sendKeys(Keys.ENTER);

        return new UserStatusPage(getDriver()).waitUntilPageLoadJS();
    }

    public List<String> searchResults() {
        List<String> textOfResults = new ArrayList<>();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.className("jenkins-command-palette__results__heading")));

        List<WebElement> searchResultsItems = searchResults;
        searchResultsItems.forEach(el -> textOfResults.add(el.getText()));

        return textOfResults;
    }

    public FreestyleProjectStatusPage moveAndClickResult(){
        PageUtils.clickJS(getDriver(), searchResults.get(0));

        return new FreestyleProjectStatusPage(getDriver()).waitUntilPageLoadJS();
    }
}
