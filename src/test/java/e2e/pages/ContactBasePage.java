package e2e.pages;

import enums.ContactButtons;
import enums.ContactTabs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactBasePage extends PageBase {
    public ContactBasePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='search']")
    WebElement inlineFilter;
    @FindBy(xpath = "//*[@rolle=dialog']")
    WebElement dialog;

    public void openTab(ContactTabs tab) {
        driver.findElement(By.xpath(tab.value)).click();


    }

    public void filterBy(String value) {
        inlineFilter.sendKeys(value);
    }

    public void clickOnBatton(ContactButtons button) {
        driver.findElement(By.xpath(button.value)).click();
    }
}
