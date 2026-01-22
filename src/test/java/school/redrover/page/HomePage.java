package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.PageUtils;

import java.util.Arrays;
import java.util.List;


public class HomePage extends BasePage<HomePage> {

    @FindBy(xpath = "//a[@href='/view/all/newJob']")
    private WebElement sidebarNewItem;

    @FindBy(xpath = "//a[@href='newJob']")
    private WebElement createJobButton;

    @FindBy(css = "[href='/newView']")
    private WebElement createNewItemOnPageWithJob;

    @FindBy(xpath = "//div[@class='tabBar']/div")
    private List<WebElement> viewNameList;

    @FindBy(xpath = "//div/section[2]/ul/li[1]/a")
    private WebElement setUpAnAgentButton;

    @FindBy(linkText = "Build Executor Status")
    private WebElement buildExecutorStatusButton;

    @FindBy(xpath = "//div[2]/span/a")
    private WebElement buildHistoryButton;

    @FindBy(css = "[initialsortdir='down'] [class='sortheader']")
    private WebElement nameColumnHeading;

    @FindBy(xpath = "//span[text()='Configure a cloud']")
    private WebElement configureCloudLink;

    @FindBy(css = "svg[tooltip='Disabled']")
    private WebElement iconDisabled;

    @FindBy(css = ".jenkins-table__link >span:first-child")
    private List<WebElement> projectsNames;

    @FindBy(id = "systemmessage")
    private WebElement systemMessage;

    @FindBy(xpath = ".//a[span[text()='Learn more about distributed builds']]")
    private WebElement learnMoreDistributedBuildsLink;

    @FindBy(css = "[class*='job-status'] td:first-child svg")
    private WebElement statusProjectIcon;

    @FindBy(css = "div[data-tippy-root]")
    private WebElement statusProjectIconTooltip;

    @FindBy(xpath = "//a[contains(@class, 'jenkins-dropdown__item') and contains(., 'Move')]")
    private WebElement moveDropDownMenuItem;

    @FindBy(xpath = "//a[contains(@class, 'jenkins-dropdown__item') and contains(., 'Pipeline Syntax')]")
    private WebElement syntaxDropDownMenuItem;

    @FindBy(xpath = "//a[contains(@href,'configure')]")
    private WebElement configureDropDownMenuItem;

    @FindBy(xpath = "//button[contains(@class, 'jenkins-dropdown__item') and contains(., 'Delete')]")
    private WebElement deleteDropDownMenuItem;

    @FindBy(xpath = "//dialog[@open]//button[@data-id='ok']")
    private WebElement yesDeleteButton;

    @FindBy(xpath = "//dialog[@open]//button[@data-id='cancel']")
    private WebElement cancelDeleteButton;

    @FindBy(xpath = "//a[@data-title='Delete View']")
    private WebElement deleteViewSidebarItem;

    @FindBy(tagName = "p")
    private WebElement paragraph;

    @FindBy(id = "description-content")
    private WebElement description;

    @FindBy(id = "description-link")
    private WebElement editDescriptionButton;

    @FindBy(name = "description")
    private WebElement descriptionTextArea;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    @FindBy(css = "div#executors")
    private WebElement buildExecutorStatusExpandableContentArea;

    @FindBy(css = "span[tooltip*='executors busy']")
    private WebElement buildExecutorStatusTooltip;

    @FindBy(className = "executors-collapsed")
    private WebElement buildExecutorStatusItem;

    @FindBy(css = ".jenkins-icon-size > :nth-child(1) > ol > li[tooltip]")
    private WebElement iconSizeTooltip;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage getPage() {
        return this;
    }

    public NewItemPage clickCreateJob() {
        createJobButton.click();

        return new NewItemPage(getDriver()).waitUntilPageLoadJS();
    }

    public NewItemPage clickSidebarNewItem() {
        sidebarNewItem.click();

        return new NewItemPage(getDriver()).waitUntilPageLoadJS();
    }

    public List<String> getProjectsNamesList() {
        return projectsNames
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public <ProjectStatusPage extends BaseProjectStatusPage<ProjectStatusPage, ?>>
    ProjectStatusPage openProject(String jobName, ProjectStatusPage projectStatusPage) {

        PageUtils.clickJS(getDriver(), By.xpath("//span[text()='%s']".formatted(jobName.trim())));

        return projectStatusPage.waitUntilPageLoadJS();
    }

    public CloudsPage clickConfigureCloud() {
        PageUtils.clickJS(getDriver(), configureCloudLink);

        return new CloudsPage(getDriver()).waitUntilPageLoadJS();
    }

    public String getFoundItemText(String itemName) {
        return getDriver().findElement(By
                .xpath("//a[@href='job/" + itemName + "/']")).getText();
    }

    public String getSystemMessageText() {
        return systemMessage.getText();
    }

    public HomePage openDropdownMenu(String itemName) {
        WebElement dropdownButton = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//a[.//span[text()='%s']]//button[@class='jenkins-menu-dropdown-chevron']".formatted(itemName))));

        PageUtils.mouseEnterJS(getDriver(), dropdownButton);
        PageUtils.clickJS(getDriver(), dropdownButton);

        return this;
    }

    public MovePage clickMoveInDropdownMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(moveDropDownMenuItem)).click();
        return new MovePage(getDriver()).waitUntilPageLoadJS();
    }

    public PipelineProjectSyntaxPage clickPipelineSyntaxInDropdownMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(syntaxDropDownMenuItem)).click();

        return new PipelineProjectSyntaxPage(getDriver()).waitUntilPageLoadJS();
    }

    public HomePage clickDeleteItemInDropdownMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(deleteDropDownMenuItem)).click();

        return this;
    }

    public FreestyleProjectConfigurationPage clickConfigureInDropdownMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(configureDropDownMenuItem)).click();

        return new FreestyleProjectConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public HomePage confirmDelete() {
        getWait2().until(ExpectedConditions.elementToBeClickable(yesDeleteButton)).click();
        // getWait5().until(ExpectedConditions.stalenessOf(yesDeleteButton));

        return this.waitUntilPageLoadJS();
    }

    public HomePage cancelDelete() {
        getWait2().until(ExpectedConditions.elementToBeClickable(cancelDeleteButton)).click();
        //getWait5().until(ExpectedConditions.stalenessOf(cancelDeleteButton));

        return this.waitUntilPageLoadJS();
    }

    public CreateViewPage clickPlusToCreateView() {
        createNewItemOnPageWithJob.click();

        return new CreateViewPage(getDriver()).waitUntilPageLoadJS();
    }

    public HomePage clickViewName(String viewName) {
        getDriver().findElement(By.linkText(viewName)).click();

        return new HomePage(getDriver());
    }

    public HomePage clickDeleteViewOnSidebar() {
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteViewSidebarItem)).click();

        return this;
    }

    public HomePage clickYesToConfirmDelete() {
        String urlBeforeDelete = getDriver().getCurrentUrl();

        getWait5().until(ExpectedConditions.elementToBeClickable(yesDeleteButton)).click();

        getWait5().until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlBeforeDelete)));

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public int getSizeOfViewNameList() {
        return viewNameList.size();
    }

    public String getParagraghText() {
        return getWait2().until(ExpectedConditions.visibilityOf(paragraph)).getText();
    }

    public HomePage clickDescription() {
        getWait2().until(ExpectedConditions.elementToBeClickable(editDescriptionButton)).click();
        return this;
    }

    public HomePage sendDescriptionText(String text) {
        getWait2().until(ExpectedConditions.visibilityOf(descriptionTextArea)).sendKeys(text);
        return this;
    }

    public String getDescriptionText() {
        return getWait2().until(ExpectedConditions.visibilityOf(description)).getText();
    }

    public HomePage clearDescriptionText() {
        getWait2().until(ExpectedConditions.visibilityOf(descriptionTextArea)).clear();
        return this;
    }

    public HomePage submitDescription() {
        submitButton.click();

        return this.waitUntilPageLoadJS();
    }

    public String getStatusProjectIconTooltipText(String projectName) {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//*[@id='job_%s']/td[1]/div".formatted(projectName))))
                .moveToElement(statusProjectIcon)
                .perform();

        return getWait5().until(ExpectedConditions.visibilityOf(statusProjectIconTooltip)).getText();
    }

    public String getNumberOfExecutors() {
        getWait2().until(ExpectedConditions.visibilityOf(buildExecutorStatusExpandableContentArea));

        String executorsLine;
        if (buildExecutorStatusExpandableContentArea.getAttribute("class").contains("expanded")) {
            executorsLine = getWait2().until(ExpectedConditions.visibilityOf(buildExecutorStatusTooltip))
                    .getAttribute("tooltip");
        } else {
            executorsLine = getWait2().until(ExpectedConditions.visibilityOf(buildExecutorStatusItem))
                    .getText();
        }

        return Arrays.stream(executorsLine.trim().split(" "))
                .skip(2)
                .findFirst()
                .orElse(null);
    }

    public int getCountOfDisplayedColumnsOnDashboard() {
        List<WebElement> columnsList = getDriver().findElements(By.cssSelector("#projectstatus > thead > tr > th"));

        return columnsList.size();
    }

    public EditViewPage clickEditViewButton(String listViewName) {
        getWait10().until(ExpectedConditions.elementToBeClickable(By
                .xpath(".//a[@href='/view/%s/configure']".formatted(listViewName)))).click();

        return new EditViewPage(getDriver()).waitUntilPageLoadJS();
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public NewNodePage clickSetUpAnAgent() {
        setUpAnAgentButton.click();

        return new NewNodePage(getDriver()).waitUntilPageLoadJS();
    }

    public NodesPage clickBuildExecutorStatus() {
        buildExecutorStatusButton.click();

        return new NodesPage(getDriver()).waitUntilPageLoadJS();
    }

    public BuildHistoryOfJenkinsPage clickBuildHistory() {
        buildHistoryButton.click();

        return new BuildHistoryOfJenkinsPage(getDriver()).waitUntilPageLoadJS();
    }

    public ArchitectingForScalePage clickLearnMoreAboutDistributedBuildsLink() {
        learnMoreDistributedBuildsLink.click();
        Object[] windowHandles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) windowHandles[1]);

        return new ArchitectingForScalePage(getDriver()).waitUntilPageLoadJS();
    }

    public boolean isBuildButtonVisible(String projectName) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//a[@title='Schedule a Build for %s'])[1]".formatted(projectName)))).isDisplayed();
    }

    public boolean isIconTableVisible(String projectName) {

        return getWait5().until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#job_%s > td:nth-child(1)".formatted(projectName))))).isDisplayed();
    }

    public MultiConfigProjectStatusPage clickProject(String projectName) {
        getDriver().findElement(By.cssSelector("#job_%s > td:nth-child(3) > a".formatted(projectName))).click();

        return new MultiConfigProjectStatusPage(getDriver()).waitUntilPageLoadJS();
    }

    public String getNameColumnText() {
        return nameColumnHeading.getText().replace("\n ", "");
    }

    public HomePage changeIconSize(String size) {
        int z = 0;
        if (size.equals("Small")) {
            z = 1;
        } else if (size.equals("Medium")) {
            z = 2;
        } else z = 3;

        getDriver().findElement(By.cssSelector("#main-panel > div.dashboard > div.jenkins-mobile-hide > div.jenkins-icon-size > div.jenkins-icon-size__items.jenkins-buttons-row > ol > li:nth-child(%s) > a"
                .formatted(z))).click();
        return new HomePage(getDriver());
    }

    public String checkIconSize() {

        return getWait2().until(ExpectedConditions.visibilityOf(iconSizeTooltip)).getAttribute("title");
    }

    public boolean isDisabledIconDisplayed() {

        return iconDisabled.isDisplayed();
    }
}
