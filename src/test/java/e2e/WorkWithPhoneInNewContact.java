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

    public void workWithPhoneInNewContact() throws IOException {
        String phoneNumber = "1234567890"; //переменная, с данными для заполнения формы
        String editPhone = "0987654321";

        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int id = createdContact.getInt("id");
        logger.debug(String.valueOf(id));

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
        phonePage.waitForLoading();
        phonePage.clickAddPhoneButton();
        phonePage.waitForDialog();
        phonePage.setAddPhoneDialog(CountryCodes.UKRAINE.getDescription(), phoneNumber);
//        phonePage.setForm(CountryCodes.UKRAINE, phoneNumber);
//        boolean visibleStatus = phonePage.saveChanges();
//        Assert.assertFalse(visibleStatus, "Save button is visible"); //после нажатия на save, мы эту кнопку больше не должны видеть

        phonePage.clickSaveButton();

        // удостовериться, что диалог исчез
        //   phonePage.confirmSaveDialogClosed();

        // проверяем уведомления (тост))
        phonePage.handleSuccessfulToast();
        phonePage.isVisiblePhone(phoneNumber);

        // проверяем, что сохранился CountryCode
        String actualCountryCode = phonePage.getTextFromCountryCodeCell(CountryCodes.UKRAINE.getCode());
        Assert.assertEquals(actualCountryCode, CountryCodes.UKRAINE.getCode(), "Actual phone number does not match  expected");

        // проверяем, что сохранился CountryCode
        String actualPhoneNumber = phonePage.getTextFromPhoneNumberCell(editPhone);
        Assert.assertEquals(actualPhoneNumber, editPhone, "Actual phone number does not match  expected");

        // выбираем редактирование в выпадающем списке
        phonePage.openGearDropdown(phoneNumber);
        phonePage.openEditDropdown();

        // ждем появления диалога редактирования
        phonePage.waitForDialog();

        // заполняем новые данные в инпутах
        phonePage.setForm(CountryCodes.FRANCE, editPhone);

        // сохраняем изменения нажатием на saveButton
        phonePage.clickSaveButton();

//        boolean visibleStatusSave = phonePage.saveChanges();
//        Assert.assertFalse(visibleStatusSave, "Save button is visible"); //после нажатия на save, мы эту кнопку больше не должны видеть

        // проверяем уведомления (тост))
        phonePage.handleSuccessfulToast();

        //удаляем телефон
        phonePage.openGearDropdown(phoneNumber);
        phonePage.openRemoveDropdown();

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
