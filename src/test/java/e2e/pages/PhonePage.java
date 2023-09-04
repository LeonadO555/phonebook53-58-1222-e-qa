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

    @FindBy(xpath = "//*[@class='btn btn-info text-white']")
    WebElement addPhoneNumberButton;

    @FindBy(xpath = "//*[@class='custom-select']")
    WebElement countryCodeDropdown;


    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberField;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement savePhoneButton;

    @FindBy(xpath = "//*[text()='Phone number saved']")
    WebElement successfulToast;

    @FindBy(xpath = "//*[@id='contact-first-name']")
    WebElement contactFirstName;

    @FindBy(xpath = "//*[@id='contact-last-name']")
    WebElement contactLastName;

    @FindBy(xpath = "//*[@id='contact-description']")
    WebElement contactDescription;

    @FindBy(xpath = "//*[@class='dropdown-item btn-phone-edit']")
    WebElement editButton;

    @FindBy(xpath = "//*[@class='dropdown-item btn-phone-remove']")
    WebElement removeButton;


    public void selectCountryCode(CountryCodes countryCodes) {
        selectDropdownText(countryCodeDropdown, countryCodes.code);
    }

    public WebElement makeCellRow(String phone) {
        return driver.findElement(By.xpath("//*[contains(@ng-reflect-result ,'" + phone + "')]"));
    }


    public void openDropDown(String phone) {
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result ,'" + phone + "')]/ancestor::tr//*[@class='nav-item ml-auto dropdown']")).click();
    }

    public void waitForLoading() {
        getWait().forVisibility(editButton);
        getWait().forClickable(editButton);
        //getWait().forVisibility(contactFirstName);
        // getWait().forVisibility(contactLastName);
        // getWait().forVisibility(contactDescription);
    }

}
