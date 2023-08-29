package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.LoginPage;
import enums.ContactButtons;
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
    Faker faker = new Faker();

    @Test(description = "User can add, edit and delete phone for new contact")
    @Feature("Phone")
    @Story("Phone page")
    @Severity(SeverityLevel.CRITICAL)
    public void createContactViaApiEditContactViaUiTest() throws IOException {
        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.internet().uuid();

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
        contactInfoPage.clickOnButton(ContactButtons.EDIT);
        contactInfoPage.waitForEditForm();
        contactInfoPage.setEditForm(editFirstName, editLastName, editDescription);
        boolean visibleStatus = contactInfoPage.saveChanges();
        Assert.assertFalse(visibleStatus, "Save button is visible"); //после нажатия на save, мы эту кнопку больше не должны видеть
        contactInfoPage.waitForLoading();


    }
}
