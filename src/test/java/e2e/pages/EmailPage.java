package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage extends ContactInfoPage {

    public EmailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='nav-link active']")
    WebElement emailTab;

    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addEmailButton;

    @FindBy(xpath = "//*[@id='input-email']")
    WebElement emailDialog;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@class='dropdown-item btn-email-edit']")
    WebElement editButton;

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@class='dropdown-menu show']")
    WebElement menuDropdown;

    @FindBy(xpath = "//*[@class='dropdown-item btn-email-remove']")
    WebElement deleteButton;

    public void waitForLoading() {
        getWait().forVisibility(emailDialog);
        getWait().forClickable(saveButton);
    }

    public void openAddDialog() {
        addEmailButton.click();
    }

    public void setEmail(String email) {
        emailDialog.click();
        emailDialog.clear();
        emailDialog.sendKeys(email);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public boolean makeCellEmail(String email) {
        return driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + email + "')]")).isDisplayed();
    }

    public void openDropdown(String email) {
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + email + "')]/ancestor::tr//*[@class='nav-item w-5 dropdown']")).click();
    }

    public void openEditDialog() {
        editButton.click();
    }

    public void deleteEmail() {
        deleteButton.click();
    }

}
