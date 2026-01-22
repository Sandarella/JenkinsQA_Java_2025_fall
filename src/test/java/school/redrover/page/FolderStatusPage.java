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

    @FindBy(xpath = "//div[@class='jenkins-breadcrumbs']//a")
    private List<WebElement> breadcrumbsMenuList;

    @FindBy(xpath = "//dialog[@open]//button[@data-id='ok']")
    private WebElement confirmOkButton;

    @FindBy(xpath = "//div[@class='tippy-content']//div[@class='jenkins-dropdown']//a[normalize-space()='Rename']")
    private WebElement renameSideMenuButton;

    @FindBy(xpath = "//button[contains(@class, 'jenkins-dropdown__item') and contains(., 'Delete')]")
    private WebElement deleteSideMenuButton;

    @FindBy(css = "#description-link")
    private WebElement addDescriptionButton;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionTextArea;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(css = "#description-content")
    private WebElement descriptionInfo;

    @FindBy(css = ".empty-state-block>section>h2")
    private WebElement folderContext;

    @FindBy(css = ".jenkins-table__link >span:first-child")
    private List<WebElement> projectList;

    @FindBy(xpath = "//*[@aria-describedby]")
    private WebElement projectTooltip;

    @FindBy(xpath = "//tr[contains(@class, 'job')]/td[1]//*[@tooltip]")
    private List<WebElement> statusIconProjectList;

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
        List<WebElement> breadcrumbElements = getWait5()
                .until(ExpectedConditions.visibilityOfAllElements(breadcrumbsMenuList));

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
        confirmOkButton.click();

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public FolderStatusPage confirmDeleteChildFolder() {
        confirmOkButton.click();

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
        renameSideMenuButton.click();

        return new ProjectRenamingPage<>(getDriver(), FolderStatusPage.class);
    }

    public FolderStatusPage clickDeleteItemInDropdownMenu() {
        deleteSideMenuButton.click();

        return this.waitUntilPageLoadJS();
    }

    public FolderStatusPage clickAddDescriptionButton() {
        addDescriptionButton.click();

        return this.waitUntilPageLoadJS();
    }

    public FolderStatusPage addDescriptionAndSave(String description) {
        descriptionTextArea.sendKeys(description);
        saveButton.click();

        return this.waitUntilPageLoadJS();
    }

    public String getDescriptionText() {
        return descriptionInfo.getText();
    }

    public String getFolderContext() {
        return folderContext.getText();
    }

    public List<String> getProjectList() {
        return projectList
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
        WebElement folderStatusIcon = getDriver().findElement(By.xpath(
                "//tr[td//a[span[text()='%s']]]//*[contains(@class, 'symbol-folder-outline')]".formatted(folderName)));
        new Actions(getDriver())
                .moveToElement(folderStatusIcon)
                .perform();

        String folderTooltipIDByAttribute = projectTooltip.getAttribute("aria-describedby");

        return getDriver().findElement(By.xpath("//*[@id='%s']/div/div".formatted(folderTooltipIDByAttribute))).getText();
    }

    public List<String> getItemsWithTooltip(String expectedTooltip) {
        Actions actions = new Actions(getDriver());
        List<String> itemsWithTooltip = new ArrayList<>();
        for (WebElement statusIcon : statusIconProjectList) {
            actions
                    .moveToElement(statusIcon)
                    .perform();
            String itemTooltipIDByAttribute = projectTooltip.getAttribute("aria-describedby");
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