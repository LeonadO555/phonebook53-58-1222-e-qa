package e2e.pages;

import enums.CountryCodes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class PhonePage extends ContactBasePage {

    public PhonePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@id = 'ngb-nav-35']")
    WebElement phonesTab;

    @FindBy(xpath = "//*[@class='btn btn-info text-white']")
    WebElement addPhoneNumberButton;

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@class='custom-select']")
    WebElement countryCodeDropdown;

    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@type='submit']")
    WebElement savePhoneButton;

    @FindBy(xpath = "//*[@class='toast-body']")
    WebElement successfulToast;

    @FindBy(xpath = "//*[@class = 'dropdown-item btn-phone-edit']")
    WebElement editButton;

    @FindBy(xpath = "//*[@class = 'dropdown-item btn-phone-remove']")
    WebElement removeButton;

    public void waitForLoading() {
        getWait().forVisibility(addPhoneNumberButton);
        getWait().forClickable(addPhoneNumberButton);
    }

    public void clickAddPhoneButton() {
        addPhoneNumberButton.click();
    }

    public void waitForDialog() {
        getWait().forVisibility(dialog);
    }

    public void setForm(CountryCodes countryCode, String phoneNumber) {
        selectDropdownText(countryCodeDropdown, countryCode.description);
        phoneNumberInput.click();
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void setAddPhoneDialog(String countryCode, String phoneNumber) {
        Select select = new Select(countryCodeDropdown);
        select.selectByVisibleText(countryCode);
        phoneNumberInput.click();
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phoneNumber);
    }

//    public void setForm(CountryCodes countryCode, String phoneNumber) {
//        Select select = new Select(countryCodeDropdown);
//        select.selectByVisibleText(countryCode.code);
//        phoneNumberInput.click();
//        phoneNumberInput.clear();
//        phoneNumberInput.sendKeys(phoneNumber);
//    }


//    public boolean saveChanges() {
//        try {
//            // savePhoneButton.isDisplayed();
//            savePhoneButton.click();
//            return true;
//        } catch (ElementClickInterceptedException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public void clickSaveButton() {
        savePhoneButton.click();
    }

    public void confirmSaveDialogClosed() {
        getWait().forInvisibility(savePhoneButton);
    }

    public void handleSuccessfulToast() {
        Assert.assertTrue(successfulToast.isDisplayed(), "Toast is not visible");
        String toastText = successfulToast.getText();
        Assert.assertEquals(toastText, "Phone  changed");
    }

//    public WebElement makeCountryCodeCellLocator(CountryCodes countryCode) {
//        return driver.findElement(By.xpath("//*[@class='table table-striped']//*[@class='row-table-cc']/ancestor::tr//*[@ng-reflect-result, '" + countryCode + "']"));
//    }

    public String getTextFromCountryCodeCell(String countryCode) {
        WebElement element = driver.findElement(By.xpath("//*[@class='table table-striped']//*[@class='row-table-cc']/ancestor::tr//*[@ng-reflect-result, '" + countryCode + "']"));
        return element.getText();
    }

    public WebElement makePhoneNumberCellLocator(String phone) {
        return driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + phone + "')]"));
    }

    public String getTextFromPhoneNumberCell(String phoneNumber) {
        WebElement element = driver.findElement(By.xpath("//*[@class='table table-striped']//*[@class='row-table-pn']//*[contains(text(), '" + phoneNumber + "')]"));
        return element.getText();
    }

    public boolean isVisiblePhone(String phone) { //можно проверить видимый ли номер телефона
        return makePhoneNumberCellLocator(phone).isDisplayed();
    }

    public void openGearDropdown(String phone) { //gear button
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + phone + ")]/ancestor::tr//*[@class='nav-item ml-auto dropdown']")).click();
    }

    public void openEditDropdown() {
        editButton.click();
    }

    public void openRemoveDropdown() {
        removeButton.click();
    }
}
