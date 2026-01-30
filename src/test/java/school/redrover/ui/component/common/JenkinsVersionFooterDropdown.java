package school.redrover.ui.component.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.ui.component.BaseComponent;

import java.util.List;


public class JenkinsVersionFooterDropdown extends BaseComponent<JenkinsVersionFooterDropdown> {

    @FindBy(className = "jenkins-dropdown__item")
    List<WebElement> dropdownElements;


    public JenkinsVersionFooterDropdown(WebDriver driver) {
        super(driver);
    }

    @Override
    public JenkinsVersionFooterDropdown waitUntilComponentLoad() {
        getWait5().until(ExpectedConditions.elementToBeClickable(dropdownElements.get(0)));

        return this;
    }

    public List<String> getDropdownElementsTextList() {
        return dropdownElements
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}