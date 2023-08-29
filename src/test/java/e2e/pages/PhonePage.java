package e2e.pages;

import enums.CountryCodes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhonePage extends ContactBasePage {

    public PhonePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@id = 'ngb-nav-35']")
    WebElement phonesTab;

    @FindBy(xpath = "//*[@class='btn btn-info text-white']")
    WebElement addPhoneNumberButton;

    @FindBy(xpath = "//*[@class='custom-select']")
    WebElement countryCodeDropdown;

    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberField;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement savePhoneButton;

    @FindBy(xpath = "//*[@class='toast-body']")
    WebElement successfulToast;

    @FindBy(xpath = "//*[@class= 'd-block fade modal show']")
    WebElement addPhoneNumberForm;

    @FindBy(xpath = "//*[@class = 'dropdown-item btn-phone-edit']")
    WebElement editButton;

    @FindBy(xpath = "//*[@class = 'dropdown-item btn-phone-edit']")
    WebElement removeButton;

    public void waitForLoading() {
        getWait().forVisibility(addPhoneNumberButton);
        getWait().forClickable(addPhoneNumberButton);
        getWait().forVisibility(countryCodeDropdown);
        getWait().forVisibility(phoneNumberField);
        getWait().forVisibility(savePhoneButton);
    }


    public void selectCountryCodes(CountryCodes countryCodes) {
        selectDropdownText(countryCodeDropdown, countryCodes.code);
    }

    public WebElement makeCellRow(String phone) {
        return driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + phone + "')]"));
    }

    public void openDropdown(String phone) { //gear button
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + phone + ")]/ancestor::tr//*[@class='nav-item ml-auto dropdown']")).click();
    }

}
