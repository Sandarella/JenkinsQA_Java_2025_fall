package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.PageUtils;
import school.redrover.component.project.status_page.sidebar.FolderSidebar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FolderStatusPage extends BaseProjectStatusPage<FolderStatusPage, FolderSidebar> {

    @FindBy(css = "[tooltip='New View']")
    private WebElement newView;

    @FindBy(id = "view-message")
    private WebElement viewMessage;

    public FolderStatusPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FolderSidebar getSidebarComponent() {
        return new FolderSidebar(getDriver()).waitUntilComponentLoad();
    }

    @Override
    public FolderStatusPage getPage() {
        return this;
    }

    public FolderInfo getInfo() {
        String displayName = getHeaderText();
        String description = viewMessage.getText();

        return new FolderInfo(displayName, description);
    }


    public static class FolderInfo {
        private final String displayName;
        private final String description;

        public FolderInfo(String displayName, String description) {
            this.displayName = displayName;
            this.description = description;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getDescription() {
            return description;
        }
    }

    public List<String> getBreadcrumbTexts() {
        List<WebElement> breadcrumbElements = getWait2().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ol[@id='breadcrumbs']/li/a")));

        List<String> breadcrumbTexts = new ArrayList<>();
        for (WebElement element : breadcrumbElements) {
            breadcrumbTexts.add(element.getText());
        }

        return breadcrumbTexts;
    }

    public FolderStatusPage openFolderPage(String folderName) {
        getDriver().findElement(By.linkText(folderName)).click();

        return this.waitUntilPageLoadJS();
    }

    public HomePage confirmDeleteFolder() {
        getDriver().findElement(By.xpath("//dialog[@open]//button[@data-id='ok']")).click();

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public FolderStatusPage confirmDeleteChildFolder() {
        getDriver().findElement(By.xpath("//dialog[@open]//button[@data-id='ok']")).click();

        return this.waitUntilPageLoadJS();
    }

    public FolderStatusPage openDropdownMenu(String itemName) {
        WebElement dropdownButton = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//a[.//span[text()='%s']]//button[@class='jenkins-menu-dropdown-chevron']".formatted(itemName))));

        PageUtils.mouseEnterJS(getDriver(), dropdownButton);
        PageUtils.clickJS(getDriver(), dropdownButton);

        return this.waitUntilPageLoadJS();
    }

    public ProjectRenamingPage<FolderStatusPage> clickRenameItemInDropdownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//div[@class='tippy-content']//div[@class='jenkins-dropdown']//a[normalize-space()='Rename']")))
                .click();

        return new ProjectRenamingPage<>(getDriver(), FolderStatusPage.class);
    }

    public FolderStatusPage clickDeleteItemInDropdownMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//button[contains(@class, 'jenkins-dropdown__item') and contains(., 'Delete')]")))
                .click();

        return this.waitUntilPageLoadJS();
    }

    public FolderStatusPage clickAddDescriptionButton() {
        getDriver().findElement(By.id("description-link")).click();

        return this.waitUntilPageLoadJS();
    }

    public FolderStatusPage addDescriptionAndSave(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
        getDriver().findElement(By.name("Submit")).click();

        return this.waitUntilPageLoadJS();
    }

    public String getDescription() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText();
    }

    public String getFolderContext() {
        return getWait2()
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".empty-state-block>section>h2")))
                .getText();
    }

    public List<String> getProjectList() {
        return getDriver().findElements(By.cssSelector(".jenkins-table__link >span:first-child"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public FolderStatusPage clickBreadcrumbsItem(String folderName) {
        getDriver().findElement(By.xpath("//a[text()='%s']".formatted(folderName))).click();

        return this.waitUntilPageLoadJS();
    }

    public String getFolderIconAttribute(String folderName) {
        return getDriver().findElement(By.xpath("//tr[td[a[span[text()='%s']]]]//*[@d]".formatted(folderName))).getAttribute("d");
    }

    public List<String> getItemsWithIconAttribute(String iconAttribute) {
        List<String> itemNames = new ArrayList<>();
        for (WebElement element : getDriver().findElements(By.xpath("//tr[.//*[contains(@d,'%s')]]//a//span".formatted(iconAttribute)))) {
            itemNames.add(element.getText());
        }
        return itemNames;
    }

    public String getFolderTooltip(String folderName) {
        Actions actions = new Actions(getDriver());
        WebElement folderStatusIcon = getDriver().findElement(By.xpath(
                "//tr[td//a[span[text()='%s']]]//*[contains(@class, 'symbol-folder-outline')]".formatted(folderName)));
        actions
                .moveToElement(folderStatusIcon)
                .perform();
        String folderTooltipIDByAttribute = getDriver().findElement(By.xpath("//*[@aria-describedby]"))
                .getAttribute("aria-describedby");

        return getDriver().findElement(By.xpath("//*[@id='%s']/div/div".formatted(folderTooltipIDByAttribute))).getText();
    }

    public List<String> getItemsWithTooltip(String expectedTooltip) {
        Actions actions = new Actions(getDriver());
        List<String> itemsWithTooltip = new ArrayList<>();
        for (WebElement statusIcon : getDriver().findElements(By.xpath("//tr[contains(@class, 'job')]/td[1]//*[@tooltip]"))) {
            actions
                    .moveToElement(statusIcon)
                    .perform();
            String itemTooltipIDByAttribute = getDriver().findElement(By.xpath("//*[@aria-describedby]"))
                    .getAttribute("aria-describedby");
            String actualTooltip = getDriver().findElement(By.xpath("//*[@id='%s']/div/div".formatted(itemTooltipIDByAttribute))).getText();

            if (actualTooltip.equals(expectedTooltip)) {
                WebElement itemNameElement = statusIcon.findElement(By.xpath("./ancestor::tr[1]//a//span"));
                itemsWithTooltip.add(itemNameElement.getText());
            }
        }

        return itemsWithTooltip;
    }

    public boolean checkURLContains(String expectedPath) {
        return Objects.requireNonNull(getDriver().getCurrentUrl()).contains(expectedPath);
    }

    public FolderStatusPage openItemDropdownMenu(String itemName) {
        WebElement dropdownButton = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//a[contains(@href, '/%s')]/button[@class='jenkins-menu-dropdown-chevron']"
                        .formatted(itemName))));

        PageUtils.mouseEnterJS(getDriver(), dropdownButton);
        PageUtils.clickJS(getDriver(), dropdownButton);

        return this.waitUntilPageLoadJS();
    }

    public boolean isMenuItemInDropdownDisplayed(String menuItem) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//a[contains(@class, 'jenkins-dropdown__item') and contains(., '%s')]".formatted(menuItem))))
                .isDisplayed();
    }

    public <ProjectStatusPage extends BaseProjectStatusPage<ProjectStatusPage, ?>>
    ProjectStatusPage openSubItemPage(String itemName, ProjectStatusPage projectStatusPage) {

        PageUtils.clickJS(getDriver(), By.xpath("//span[text()='%s']".formatted(itemName.trim())));

        return projectStatusPage.waitUntilPageLoadJS();
    }

    public FolderCreateViewPage clickNewView() {
        newView.click();

        return new FolderCreateViewPage(getDriver()).waitUntilPageLoadJS();
    }
}