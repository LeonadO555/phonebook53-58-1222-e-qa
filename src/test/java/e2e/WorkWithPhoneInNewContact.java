package e2e;

import api.contact.Contact;
import api.phone.Phone;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.LoginPage;
import e2e.pages.PhonePage;
import enums.ContactTabs;
import enums.CountryCodes;
import enums.UserCredentials;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkWithPhoneInNewContact extends TestBase {

    Contact contact;

    Phone phone;
    LoginPage loginPage;
    ContactPage contactPage;
    ContactInfoPage contactInfoPage;
    PhonePage phonePage;

//    Faker faker = new Faker();

    @Test(description = "User can add, edit and delete phone for new contact")
    @Feature("Phone")
    @Story("Phone page")
    @Severity(SeverityLevel.CRITICAL)

    public void workWithPhoneInNewContact() {
        String phoneNumber = "1234567890";
        String editPhone = "0987654321";

        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int id = createdContact.getInt("id");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmSuccessfulLogin();

        contactPage = new ContactPage(app.driver);
        contactPage.waitForLoading();
        contactPage.openContactById(String.valueOf(id));

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        contactInfoPage.openTab(ContactTabs.PHONES);

        phonePage = new PhonePage(app.driver);
        phonePage.waitForLoadingAddPhoneNumberButton();
        phonePage.clickAddPhoneButton();
        phonePage.waitForDialog();
        phonePage.setPhoneDialog(CountryCodes.UKRAINE.getDescription(), phoneNumber);
        phonePage.clickSaveButton();
        phonePage.confirmSaveDialogClosed();
        phonePage.handleSuccessfulToast();

        // check if PhoneNumber saved
        phonePage.waitForLoadingFirstTableRow();
        String actualPhoneNumber = phonePage.getTextFromPhoneNumberCell(phoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumber, "Actual phone number does not match expected");

        phonePage.openGearDropdown(phoneNumber);
        phonePage.openEditDropdown();
        phonePage.waitForDialog();
        phonePage.setPhoneDialog(CountryCodes.FRANCE.getDescription(), editPhone);
        phonePage.clickSaveButton();
        phonePage.handleSuccessfulToast();


        // 1st variant: delete phone number via UI
//        phonePage.waitForLoadingFirstTableRow();
//        phonePage.openGearDropdown(editPhone);
//        phonePage.openRemoveDropdown();
//        phonePage.handleSuccessfulToast();

        // 2nd variant: delete phone number via API
        phone = new Phone();
        JsonPath createdPhone = phone.getAllPhones(200, id).jsonPath();
        int phoneId = createdPhone.getInt("[0].id");
        phone.deletePhone(200, phoneId);

        // delete edited contact via API TODO: DELETE
        contact.deleteContact(200, id);

        // get error message (not existing in DB) TODO: GET
        JsonPath actualDeletedContact = contact.getContact(500, id).jsonPath();
        Assert.assertEquals(actualDeletedContact.getString("message"), "Error! This contact doesn't exist in our DB");
    }
}
