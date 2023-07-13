package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
import e2e.pages.ContactPage;
import e2e.pages.ContactPhonesPage;
import e2e.pages.LoginPage;
import enums.ContactButtons;
import enums.ContactTabs;
import enums.CountryCodes;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateContactViaApiAddPhoneViaUiTest extends TestBase{

    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;
    ContactPhonesPage contactPhonesPage;
    Faker faker = new Faker();

    @Test
    public void createContactViaApiEditContactViaUiTest() {
        String editPhoneNumber = "1234567890";

        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int id = createdContact.getInt("id");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login();
        loginPage.confirmSuccessfulLogin();

        contactPage = new ContactPage(app.driver);
        contactPage.waitForLoading();
        contactPage.openContactById(String.valueOf(id));

        contactPhonesPage = new ContactPhonesPage(app.driver);
        contactPhonesPage.waitForLoading();
        contactPhonesPage.clickOnPhonesTab(ContactTabs.PHONES);
        contactPhonesPage.clickOnButton(ContactButtons.ADD_PHONE_NUMBER);
        contactPhonesPage.waitForEditDialog();
        contactPhonesPage.setAddPhoneDialog(CountryCodes.FRANCE.getDescription(), editPhoneNumber);
        boolean visibleStatus = contactPhonesPage.saveChanges();
        Assert.assertFalse(visibleStatus, "Save button is visible");

        contactPhonesPage.waitForLoading();
        Assert.assertEquals(contactPhonesPage.makeCountryCodeCellLocator(CountryCodes.FRANCE.getCode()).getText(), CountryCodes.FRANCE.getCode(), "Country codes are not equal");

        String actualPhoneNumber = contactPhonesPage.getTextFromPhoneNumberColumnCell(editPhoneNumber);
        Assert.assertEquals(actualPhoneNumber, editPhoneNumber, "Actual phone number does not match  expected");
    }
}
