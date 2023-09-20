package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PhonePage extends ContactBasePage {

    public PhonePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='btn btn-info text-white']")
    WebElement addPhoneNumberButton;

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@class='custom-select']")
    WebElement countryCodeDropdown;

    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@class = 'btn btn-primary']")
    WebElement savePhoneButton;

    @FindBy(xpath = "//*[@class='toast-body']")
    WebElement successfulToast;

    @FindBy(xpath = "//*[@id='items-table-phone']/tbody/tr[1]")
    WebElement firstTableRow;

    @FindBy(xpath = "//*[@class = 'container']")
    WebElement pageContainer;

    @FindBy(xpath = "//*[@class = 'dropdown-item btn-phone-edit']")
    WebElement editButton;

    @FindBy(xpath = "//*[@class = 'dropdown-item btn-phone-remove']")
    WebElement removeButton;

    public void waitForLoadingAddPhoneNumberButton() {
        getWait().forVisibility(addPhoneNumberButton);
        getWait().forClickable(addPhoneNumberButton);
    }

    public void clickAddPhoneButton() {
        addPhoneNumberButton.click();
    }

    public void waitForDialog() {
        getWait().forVisibility(dialog);
    }

    public void setPhoneDialog(String countryCode, String phoneNumber) {
        Select select = new Select(countryCodeDropdown);
        select.selectByVisibleText(countryCode);
        phoneNumberInput.click();
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void clickSaveButton() {
        savePhoneButton.click();
    }

    public void confirmSaveDialogClosed() {
        getWait().forInvisibility(savePhoneButton);
    }

    public void handleSuccessfulToast() {
        new WebDriverWait(driver, Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(successfulToast));
        Assert.assertTrue(successfulToast.isDisplayed(), "Toast is not visible");
        String toastText = successfulToast.getText();
        Assert.assertEquals(toastText, "Phone number saved");
    }

    public void waitForLoadingFirstTableRow() {
        getWait().forVisibility(firstTableRow);
    }

    public String getTextFromPhoneNumberCell(String phoneNumber) {
        WebElement element = driver.findElement(By.xpath("//*[@class='table table-striped']//*[@class='row-table-pn']//*[contains(text(), '" + phoneNumber + "')]"));
        return element.getText();
    }

    public void openGearDropdown(String phone) {
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + phone + "')]/ancestor::tr//*[@class='nav-item ml-auto dropdown']")).click();
    }

    public void openEditDropdown() {
        editButton.click();
    }

//    public void openRemoveDropdown() {
//        removeButton.click();
//    }
}
