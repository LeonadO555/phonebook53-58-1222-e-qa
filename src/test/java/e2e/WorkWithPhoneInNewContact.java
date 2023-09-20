package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
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

import java.io.IOException;

public class WorkWithPhoneInNewContact extends TestBase {

    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;
    ContactInfoPage contactInfoPage;
    PhonePage phonePage;

    Faker faker = new Faker();

    @Test(description = "User can add, edit and delete phone for new contact")
    @Feature("Phone")
    @Story("Phone page")
    @Severity(SeverityLevel.CRITICAL)

    public void workWithPhoneInNewContact() throws IOException, InterruptedException {
        String phoneNumber = "1234567890"; //переменная, с данными для заполнения формы
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

//        phonePage.isVisiblePhone(phoneNumber);
//        Thread.sleep(3000);

//         // check if CountryCode saved
//        phonePage.waitForLoadingFirstTableRow();
//        String actualCountryCode = phonePage.getTextFromCountryCodeCell(CountryCodes.UKRAINE.getCode());
//        Assert.assertEquals(actualCountryCode, CountryCodes.UKRAINE.getCode(), "Actual phone number does not match  expected");

        // check if PhoneNumber saved
        phonePage.waitForLoadingFirstTableRow();
        String actualPhoneNumber = phonePage.getTextFromPhoneNumberCell(phoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumber, "Actual phone number does not match  expected");

        phonePage.openGearDropdown(phoneNumber);
        phonePage.openEditDropdown();
        phonePage.waitForDialog();
        phonePage.setPhoneDialog(CountryCodes.FRANCE.getDescription(), editPhone);
        phonePage.clickSaveButton();
        phonePage.handleSuccessfulToast();

        phonePage.waitForLoadingFirstTableRow();
        phonePage.openGearDropdown(editPhone);
        phonePage.openRemoveDropdown();
        phonePage.handleSuccessfulToast();

//        //или удаляем телефон через API
//        phone.deletePhone(200, id);
//        phone.getPhone(200, id);
//        contact.deleteContact(200, id);
//        contact.getContact(200, id);

        // удаляем контакт через API
        // delete edited contact TODO: DELETE
        contact.deleteContact(200, id);

        // get error message (not existing in DB) TODO: GET
        JsonPath actualDeletedContact = contact.getContact(500, id).jsonPath();
        Assert.assertEquals(actualDeletedContact.getString("message"), "Error! This contact doesn't exist in our DB");
    }

}
