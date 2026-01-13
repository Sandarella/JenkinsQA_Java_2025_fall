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

    @FindBy(css = "[class*=\"job-status\"] td:first-child svg")
    private WebElement statusTooltipProjectIcon;

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

    @FindBy (css = "svg[tooltip='Disabled']")
    private WebElement iconDisabled;

    @FindBy(css = ".jenkins-table__link >span:first-child")
    private List<WebElement> projectsNames;

    @FindBy (id = "systemmessage")
    private WebElement systemMessage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage getPage() {
        return this;
    }

    @Override
    public HomePage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.elementToBeClickable(sidebarNewItem));

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

    public <ProjectStatusPage extends BaseProjectStatusPage<ProjectStatusPage>>
    ProjectStatusPage openProject(String jobName, ProjectStatusPage projectStatusPage) {

        PageUtils.clickJS(getDriver(), By.xpath("//span[text()='%s']".formatted(jobName.trim())));

        return projectStatusPage.waitUntilPageLoadJS();
    }

    public CloudsPage clickConfigureCloud() {
        PageUtils.clickJS(getDriver(), configureCloudLink);

        return new CloudsPage(getDriver()).waitUntilPageLoadJS();
    }


    public WebElement findItem(String itemName) {
        return getDriver().findElement(By.xpath("//a[@href='job/" + itemName + "/']"));
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
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'jenkins-dropdown__item') and contains(., 'Move')]"))).click();

        return new MovePage(getDriver());
    }

    public PipelineSyntaxPage clickPipelineSyntaxInDropdownMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'jenkins-dropdown__item') and contains(., 'Pipeline Syntax')]"))).click();

        return new PipelineSyntaxPage(getDriver());
    }

    public HomePage clickDeleteItemInDropdownMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'jenkins-dropdown__item') and contains(., 'Delete')]"))).click();

        return this;
    }

    public FreestyleProjectConfigurationPage clickConfigureInDropdownMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'configure')]"))).click();

        return new FreestyleProjectConfigurationPage(getDriver());
    }

    public HomePage confirmDelete() {
        WebElement yesButton = getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//dialog[@open]//button[@data-id='ok']")));
        yesButton.click();
        getWait5().until(ExpectedConditions.stalenessOf(yesButton));

        return this;
    }

    public HomePage cancelDelete() {
        WebElement yesButton = getWait2().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//dialog[@open]//button[@data-id='cancel']"))
        );
        yesButton.click();
        getWait5().until(ExpectedConditions.stalenessOf(yesButton));

        return this;
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
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-title='Delete View']"))).click();

        return this;
    }

    public HomePage clickYesToConfirmDelete() {
        String urlBeforeDelete = getDriver().getCurrentUrl();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-id='ok']"))).click();

        getWait5().until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlBeforeDelete)));

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public int getSizeOfViewNameList() {
        return viewNameList.size();
    }

    public String getParagraghText() {
        return getWait2()
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")))
                .getText();
    }

    public HomePage clickDescription() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        return this;
    }

    public HomePage sendDescriptionText(String text) {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).sendKeys(text);
        return this;
    }

    public HomePage submitDescription() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }

    public String getDescription() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText();
    }

    public HomePage clearTextDescription() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).clear();
        return this;
    }

    public String getProjectStatus(String projectName) {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//*[@id='job_%s']/td[1]/div".formatted(projectName))))
                .perform();

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-tippy-root]")))
                .getText();
    }

    public String getNumberOfExecutors() {
        WebElement executors = getDriver().findElement(By.cssSelector("div#executors"));

        String executorsLine;
        if (executors.getAttribute("class").contains("expanded")) {
            executorsLine = getDriver().findElement(By.cssSelector("span[tooltip*='executors busy']")).getAttribute("tooltip");
        } else {
            executorsLine = getDriver().findElement(By.className("executors-collapsed")).getText();
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

    public String getStatusProjectIconTooltipTextOnHover() {
        new Actions(getDriver()).moveToElement(statusTooltipProjectIcon).perform();

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("tippy-content")))
                .getText();
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

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
        return new NodesPage(getDriver());
    }

    public BuildHistoryOfJenkinsPage clickBuildHistory() {
        buildHistoryButton.click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
        return new BuildHistoryOfJenkinsPage(getDriver());
    }

    public ArchitectingForScalePage clickLearnMoreAboutDistributedBuildsLink() {
        getDriver().findElement(By.xpath(".//a[span[text()='Learn more about distributed builds']]"))
                .click();
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

    public MultiConfigurationProjectStatusPage clickProject(String projectName) {
        getDriver().findElement(By.cssSelector("#job_%s > td:nth-child(3) > a".formatted(projectName))).click();

        return new MultiConfigurationProjectStatusPage(getDriver()).waitUntilPageLoadJS();
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

        getDriver().findElement(By.cssSelector("#main-panel > div.dashboard > div.jenkins-mobile-hide > div.jenkins-icon-size > div.jenkins-icon-size__items.jenkins-buttons-row > ol > li:nth-child(%s) > a".formatted(z))).click();
        return new HomePage(getDriver());
    }

    public String checkIconSize() {
        String result = getDriver().findElement(By.cssSelector(".jenkins-icon-size > :nth-child(1) > ol > li[tooltip]")).getAttribute("title");
        return result;
    }

    public boolean isDisabledIconDisplayed(){

        return iconDisabled.isDisplayed();
    }
}
