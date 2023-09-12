package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.LoginPage;
import e2e.pages.PhonePage;
import enums.CountryCodes;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
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
    public void createContactViaApiEditContactViaUiTest() throws IOException {
        String phone = "1111111111";
        String editPhone = "1111111115";

        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int id = createdContact.getInt("id");
        logger.debug(String.valueOf(id));

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login();
        loginPage.confirmSuccessfulLogin();

        contactPage = new ContactPage(app.driver);
        contactPage.waitForLoading();
        contactPage.openContactById(String.valueOf(id));

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();

        phonePage = new PhonePage(app.driver);
        phonePage.waitForLoading();
        phonePage.waitForOpenDialog();
        phonePage.setForm(CountryCodes.GERMANY, phone);
        phonePage.saveChanges();
        phonePage.handleSuccessfulToast();
        phonePage.isVisiblePhone(phone);
        phonePage.makeCellRow(phone);
        phonePage.setForm(CountryCodes.GERMANY, editPhone);
        phonePage.saveChanges();
        phonePage.handleSuccessfulToast();
        phonePage.deletePhone();


    }
}

