package e2e;

import api.contact.Contact;
import api.email.Email;
import com.github.javafaker.Faker;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.EmailPage;
import e2e.pages.LoginPage;
import enums.ContactInfoTabs;
import enums.UserCredentials;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEditDeleteEmailTest extends TestBase {

    EmailPage emailPage;
    Email email;
    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;
    ContactInfoPage contactInfoPage;
    Faker faker = new Faker();

    @Test
    public void createEditDeleteEmailTest() {
        String contactEmail = faker.internet().emailAddress();
        String editedContactEmail = faker.internet().emailAddress();
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
        contactInfoPage.waitForLoading();

        emailPage = new EmailPage(app.driver);
        emailPage.openTab(ContactInfoTabs.EMAILS);
        emailPage.openAddDialog();
        emailPage.setEmail(contactEmail);
        emailPage.clickSaveButton();
        Assert.assertTrue(emailPage.makeCellEmail(contactEmail));
        email = new Email();
        JsonPath createdEmail = email.getEmail(200, contactId).jsonPath();
        int emailId = createdEmail.getInt("[0].id");
        emailPage.openDropdown(contactEmail);
        emailPage.openEditDialog();
        emailPage.setEmail(editedContactEmail);
        emailPage.clickSaveButton();
        emailPage.waitForLoading();
        Assert.assertTrue(emailPage.makeCellEmail(editedContactEmail));

        emailPage.waitForLoading();
        emailPage.openDropdown(editedContactEmail);
        emailPage.deleteEmail();
        email = new Email();
        JsonPath actualDeletedEmail = email.getEmail(500, emailId).jsonPath();
        Assert.assertEquals(actualDeletedEmail.getString("message"), "Error! This email doesn't exist in our DB");

        contact.deleteContact(200, contactId);
        JsonPath actualDeletedContact = contact.getContact(500, contactId).jsonPath();
        Assert.assertEquals(actualDeletedContact.getString("message"), "Error! This contact doesn't exist in our DB");

    }
}