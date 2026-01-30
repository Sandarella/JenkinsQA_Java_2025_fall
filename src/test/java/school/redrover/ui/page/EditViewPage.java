package school.redrover.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import school.redrover.common.BasePage;
import school.redrover.common.PageUtils;
import java.util.List;


public class EditViewPage extends BasePage<EditViewPage> {

    @FindBy(xpath = "//button[@class='jenkins-dropdown__item ']")
    private List<WebElement> columnListForAdd;

    @FindBy(xpath = "//button[text()='Add column']")
    private WebElement addColumnButton;

    @FindBy(xpath = "//div[@class='repeated-chunk__header']")
    private List<WebElement> columnHeaders;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;


    public EditViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public EditViewPage getPage() {
        return this;
    }

    public EditViewPage clickAddColumnButton() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                getWait10().until(ExpectedConditions.visibilityOf(addColumnButton)));

        addColumnButton.click();

        return this;
    }

    public List<String> getCurrentColumnList() {
        return getWait10().until(ExpectedConditions.visibilityOfAllElements(columnHeaders))
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    public EditViewPage addColumnInListView() {
        List<String> currentColumnList = getCurrentColumnList();

        Assert.assertNotEquals(columnListForAdd.size(), 0);
        for (WebElement element : columnListForAdd) {
            String columnName = element.getText().trim();

            if (!currentColumnList.contains(columnName)) {
                PageUtils.mouseEnterJS(getDriver(), element);
                PageUtils.clickJS(getDriver(), element);
            }
        }
        return this;
    }

    public EditViewPage selectJobCheckbox(String jobName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//label[text()='%s']".formatted(jobName))))
                .click();

        return this;
    }

    public void clickSaveButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public EditViewPage clickDeleteButton(String columnName) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath(".//div[contains(text(),'%s')]".formatted(columnName)))));

        WebElement deleteButton = getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath(".//div[contains(text(),'%s')]/button".formatted(columnName))));
        deleteButton.click();
        getWait5().until(ExpectedConditions.stalenessOf(deleteButton));

        return this;
    }
}