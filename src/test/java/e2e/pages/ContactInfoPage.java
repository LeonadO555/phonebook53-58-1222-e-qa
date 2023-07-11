package e2e.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactInfoPage extends ContactBasePage {
    public ContactInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@name='input-ec-firstName']")
    WebElement firstNameInput;

    @FindBy(xpath = "//*[@name='input-ec-lastName']")
    WebElement lastNameInput;

    @FindBy(xpath = "//*[@name='input-ec-description']")
    WebElement descriptionTextarea;

    @FindBy(xpath = "//*[@class='col-sm-3']//*[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@class='toast-body']")
    WebElement toast;

    @FindBy(xpath = "//*[@id='contact-first-name']")
    WebElement contactFirstName;

    @FindBy(xpath = "//*[@id='contact-last-name']")
    WebElement contactLastName;

    @FindBy(xpath = "//*[@id='contact-description']")
    WebElement contactDescription;

    @FindBy(xpath = "//*[@id='btn-edit-contact']")
    WebElement editButton;

    public void waitForLoading() {
        getWait().forVisibility(editButton);
        getWait().forClickable(editButton);
        getWait().forVisibility(contactFirstName);
        getWait().forVisibility(contactLastName);
        getWait().forVisibility(contactDescription);
    }

    public void waitForEditForm() {
        getWait().forVisibility(firstNameInput);
        getWait().forVisibility(lastNameInput);
        getWait().forVisibility(descriptionTextarea);
    }

    public void setEditForm(String firstName, String lastName, String description) {
        firstNameInput.click();
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.click();
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        descriptionTextarea.click();
        descriptionTextarea.clear();
        descriptionTextarea.sendKeys(description);
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
        Assert.assertEquals(toastText, "Contact changed");
    }

    public List<String> getEditForm() {
        List<String> listEditedData = new ArrayList<>();
        listEditedData.add(contactFirstName.getText());
        listEditedData.add(contactLastName.getText());
        listEditedData.add(contactDescription.getText());
        return listEditedData;
    }
}