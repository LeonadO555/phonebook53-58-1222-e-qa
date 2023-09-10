package e2e.pages;

import enums.SortDirections;
import enums.SortValues;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

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

    @FindBy(xpath = "//*[@ng-reflect-ngb-tooltip='Permanently deleting']")
    WebElement deleteButton;

    public void waitForLoading() {
        try {
            getWait().forInvisibility(emailDialog);
            getWait().forVisibility(emailDialog);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@ng-reflect-result, '" + email + "')]")));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void openDropdown(String email) {
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + email + "')]/ancestor::tr//*[@class='nav-item w-5 dropdown']")).click();
    }

    public void clickOnSortLink(SortValues sortValue) {
        driver.findElement(By.xpath("//table[@id='items-table-email']/thead/tr/th/a/span/app-sort-icon/img")).click();
    }

    public void checkSortDirection(SortDirections direction) {
        driver.findElement(By.xpath("//*[@id=\"items-table-email\"]/thead/tr/th[1]/a/span/app-sort-icon")).isDisplayed();
    }

    public void openEditDialog() {
        editButton.click();
    }

    public void deleteEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='items-table-email']/tbody/tr[1]/td[2]/div/button[2]")));
        deleteButton.click();
    }
}
