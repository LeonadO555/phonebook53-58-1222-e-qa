package e2e.pages;

import enums.CountryCodes;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement savePhoneButton;

    @FindBy(xpath = "//*[text()='Phone number saved']")
    WebElement successfulToast;

    @FindBy(xpath = "//*[@class='dropdown-item btn-phone-edit']")
    WebElement editButton;

    @FindBy(xpath = "//*[@class='dropdown-item btn-phone-remove']")
    WebElement removeButton;


    public void waitForLoading() {
        getWait().forVisibility(addPhoneNumberButton);
        getWait().forClickable(addPhoneNumberButton);
    }

    public void waitForOpenDialog() {
        getWait().forVisibility(dialog);
    }

    public void setForm(CountryCodes countryCode, String phoneNumber) {
        selectDropdownText(countryCodeDropdown, countryCode.code);
        phoneNumberInput.click();
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public boolean saveChanges() {
        savePhoneButton.click();
        try {
            savePhoneButton.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void handleSuccessfulToast() {
        Assert.assertTrue(successfulToast.isDisplayed(), "Toast is not visible");
        String toastText = successfulToast.getText();
        Assert.assertEquals(toastText, "Phone number changed");
    }

    public WebElement makeCellRow(String phone) {
        return driver.findElement(By.xpath("//*[contains(@ng-reflect-result ,'" + phone + "')]"));
    }

    public boolean isVisiblePhone(String phone) {
        return makeCellRow(phone).isDisplayed();
    }

    public void openDropDown(String phone) {
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result ,'" + phone + "')]/ancestor::tr//*[@class='nav-item ml-auto dropdown']")).click();
    }
}