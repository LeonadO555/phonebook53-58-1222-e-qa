package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddressPage extends ContactInfoPage {

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='items-table-address']")
    WebElement tableBody;
    @FindBy(xpath = "//*[@class='btn btn-info text-white']")
    WebElement addAddressButton;

    @FindBy(xpath = "//*[@class='custom-select']")
    WebElement inputCountry;
    @FindBy(xpath = "//*[@ng-reflect-name='city']")
    WebElement inputCity;

    @FindBy(xpath = "//*[@ng-reflect-name='zip']")
    WebElement inputPostCode;

    @FindBy(xpath = "//*[@ng-reflect-name='street']")
    WebElement inputStreet;

    @FindBy(xpath = "//*[@class='btn btn-primary btn-block']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement openEditDropDown;

    @FindBy(xpath = "//*[@class='dropdown-item btn-address-edit']")
    WebElement editButton;

    @FindBy(xpath = "//*[@class='dropdown-item btn-address-remove']")
    WebElement deleteButton;

    public void waitForLoading() {
        try {
            getWait().forInvisibility(tableBody);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        getWait().forClickable(addAddressButton);
    }

    public void clickOnAddNewAddressButton() {
        addAddressButton.click();
    }

    public void fillAddressForm(String city, String postCode, String street) {
        Assert.assertFalse(inputCountry.isSelected(), "Country Germany isn't selected");
        inputCity.sendKeys(city);
        inputPostCode.sendKeys(postCode);
        inputStreet.sendKeys(street);
    }

    public void clickOnSaveButton() {
        saveButton.click();
    }

    public boolean makeCellAddress(String address) {
        return driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + address + "')]")).isDisplayed();
    }

    public void clickOnEditDropdown(String cell) {
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + cell + "')]/ancestor::tr//*[@class='nav-item ml-auto dropdown']")).click();
    }

    public void openEditDialog() {
        editButton.click();
    }

    public void deleteAddress() {
        deleteButton.click();
    }
}
