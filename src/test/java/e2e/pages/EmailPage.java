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
    @FindBy(xpath = "//*[@class='btn btn-info text-white']")
    WebElement addEmailButton;
    @FindBy(xpath = "//*[@id='input-email']")
    WebElement emailDialog;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@class='dropdown-item btn-email-edit']")
    WebElement editButton;
    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@class='dropdown-item btn-email-remove']")
    WebElement deleteDropDown;


    public void waitForLoading() {

        getWait().forVisibility(emailDialog);
        getWait().forClickable(saveButton);
    }

    public void openEditDropDown(String email) {
        driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + email + "')]/ancestor::tr//*@class='nav-item w-5 show dropdown']")).click();
        //driver.findElement(By.xpath("//*[contains(@ng-reflect-result, '" + email + "')]/ancestor::tr//*[@class='nav-item w-5 show dropdown']")).click();
    }


    public void openEditDialog() {
        editButton.click();
        ;
    }

    //public void deleteEmail() {
    //  deleteButton.click();
    // }
}

