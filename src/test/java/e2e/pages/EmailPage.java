package e2e.pages;

import enums.SortDirections;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EmailPage extends ContactInfoPage {

    public EmailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addEmailButton;

    @FindBy(xpath = "//*[@id='input-email']")
    WebElement emailDialog;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@class='dropdown-item btn-email-edit']")
    WebElement editButton;

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
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@ng-reflect-result, '" + email + "')]")));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openDropdown(String email) {
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + email + "')]/ancestor::tr//*[@class='nav-item w-5 dropdown']")).click();
    }

    public void clickOnSortLink() {
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='dropdown-item btn-email-remove']")));
        deleteButton.click();
    }

    public List<String> getTextFromEmailColumn(String email) {
        List<String> array = new ArrayList<>();
        // //*[@ng-repeat='cust in Customers | orderBy:sortType:sortReverse | filter:searchCustomer']/td[1]
        List<WebElement> cells = driver.findElements(By.xpath("//*[contains(text(), '" + email + "')]"));
        for (WebElement cell : cells) {
            String text = cell.getText();
            array.add(text);
        }
        return array;
    }
}