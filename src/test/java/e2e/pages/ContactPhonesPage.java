package e2e.pages;

import enums.ContactTabs;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactPhonesPage extends ContactBasePage {
    public ContactPhonesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='cc-select']")
    WebElement countryCodeInput;

    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@class='input-group mb-3']//*[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@class='toast-body']")
    WebElement toast;

    @FindBy(xpath = "//*[@id = 'cc-select']")
    WebElement phoneCountryCode;

    @FindBy(xpath = "//*[@ng-reflect-result='1234567890']")
    WebElement phoneNumber;

    @FindBy(xpath = "//span[@aria-hidden='true']")
    WebElement closePageButton;

    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addPhoneNumberButton;

    public void waitForLoading() {
        getWait().forVisibility(addPhoneNumberButton);
        getWait().forClickable(addPhoneNumberButton);
        getWait().forVisibility(phoneCountryCode);
        getWait().forVisibility(phoneNumber);
    }

    public void waitForEditDialog() {
        getWait().forVisibility(countryCodeInput);
        getWait().forVisibility(phoneNumberInput);
    }

    public void setAddPhoneDialog(String countryCode, String phoneNumber) {
        Select select = new Select(countryCodeInput);
        select.selectByVisibleText(countryCode);
        phoneNumberInput.click();
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public boolean saveChanges() {
        saveButton.click();
        try {
            saveButton.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void handleSuccessfulToast() {
        Assert.assertTrue(toast.isDisplayed(), "Toast is not visible");
        String toastText = toast.getText();
        Assert.assertEquals(toastText, "Phone number changed");
    }

    public List<String> getEditForm() {
        List<String> listEditedData = new ArrayList<>();
        listEditedData.add(phoneCountryCode.getText());
        listEditedData.add(phoneNumber.getText());
        return listEditedData;
    }

    public void clickOnPhonesTab(ContactTabs phones) {
        driver.findElement(By.xpath(phones.value)).click();
    }

    public WebElement makeCountryCodeCellLocator(String countryCode) {
        return driver.findElement(By.xpath("//*[@class='table table-striped']//*[@class='row-table-cc']//*[contains(text(), '" + countryCode + "')]"));
    }

    public String getTextFromPhoneNumberColumnCell(String phoneNumber) {
        WebElement element = driver.findElement(By.xpath("//*[@class='table table-striped']//*[@class='row-table-pn']//*[contains(text(), '" + phoneNumber + "')]"));
        return element.getText();
    }

}
