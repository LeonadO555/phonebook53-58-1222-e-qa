package e2e;

import api.address.Address;
import api.contact.Contact;
import com.github.javafaker.Faker;
import e2e.pages.AddressPage;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.LoginPage;
import enums.ContactInfoTabs;
import enums.UserCredentials;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEditDeleteAddresses extends TestBase {
    Contact contact;
    LoginPage loginPage;
    AddressPage addressPage;
    Address address;
    ContactPage contactPage;
    ContactInfoPage contactInfoPage;
    Faker faker = new Faker();

    @Test(description = "User can create edit and delete Address in created Contact")
    public void createEditDeleteAddresses() {
        String city = "Berlin";
        String postCode = "55003";
        String street = "Karl-Max";

        String editCity = "Hamburg";
        String editPostCode = "44205";
        String editStreet = "Friedrich";

        address = new Address();

        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int contactId = createdContact.getInt("id");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmSuccessfulLogin();

        contactPage = new ContactPage(app.driver);
        contactPage.waitForLoading();
        contactPage.openContactById(String.valueOf(contactId));

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.openTab(ContactInfoTabs.ADDRESSES);

        addressPage = new AddressPage(app.driver);
        addressPage.clickOnAddNewAddressButton();
        addressPage.fillAddressForm(city, postCode, street);
        addressPage.clickOnSaveButton();
        addressPage.waitForLoading();
        Assert.assertTrue(addressPage.makeCellAddress(city), "Address is created");

        addressPage.waitForLoading();
        JsonPath createdAddress = address.getAddresses(200, contactId).jsonPath();
        int addressId = createdAddress.getInt("[0].contactId");
        addressPage.clickOnEditDropdown(city);
        addressPage.openEditDialog();
        addressPage.fillAddressForm(editCity, editPostCode, editStreet);
        addressPage.clickOnSaveButton();
        addressPage.waitForLoading();
        Assert.assertTrue(addressPage.makeCellAddress(editCity), "Address is edited");

        addressPage.waitForLoading();
        addressPage.clickOnEditDropdown(editCity);
        addressPage.deleteAddress();
        address = new Address();
        addressPage.waitForLoading();
        JsonPath actualDeletedEmail = address.getAddress(500, addressId).jsonPath();
        Assert.assertEquals(actualDeletedEmail.getString("message"), "Error! This address doesn't exist in our DB");

        contact.deleteContact(200, contactId);
        JsonPath actualDeletedContact = contact.getContact(500, contactId).jsonPath();
        Assert.assertEquals(actualDeletedContact.getString("message"), "Error! This contact doesn't exist in our DB");
    }
}