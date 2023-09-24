package e2e;

import api.contact.Contact;
import api.email.Email;
import com.github.javafaker.Faker;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.EmailPage;
import e2e.pages.LoginPage;
import enums.ContactInfoTabs;
import enums.SortDirections;
import enums.UserCredentials;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateEditSortDeleteEmailsTest extends TestBase {

    EmailPage emailPage;
    Email email;
    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;
    ContactInfoPage contactInfoPage;
    Faker faker = new Faker();

    @Test
    public void createEditSortDeleteEmailTest() {
        List<String> emailsToCreate = new ArrayList<>();
        emailsToCreate.add(faker.internet().emailAddress());
        emailsToCreate.add(faker.internet().emailAddress());
        emailsToCreate.add(faker.internet().emailAddress());

        List<String> editedEmails = new ArrayList<>();
        editedEmails.add(faker.internet().emailAddress());
        editedEmails.add(faker.internet().emailAddress());
        editedEmails.add(faker.internet().emailAddress());

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
        for (String emailToCreate : emailsToCreate) {
            emailPage.openAddDialog();
            emailPage.setEmail(emailToCreate);
            emailPage.clickSaveButton();
            emailPage.waitForLoading();
            Assert.assertTrue(emailPage.makeCellEmail(emailToCreate));
        }
        email = new Email();
        JsonPath createdEmail = email.getEmails(200, contactId).jsonPath();
        int emailId = createdEmail.getInt("[0].id");

        for (int i = 0; i < emailsToCreate.size(); i++) {
            String originalEmail = emailsToCreate.get(i);
            String editedEmail = editedEmails.get(i);

            emailPage.openDropdown(originalEmail);
            emailPage.openEditDialog();
            emailPage.setEmail(editedEmail);
            emailPage.clickSaveButton();
            emailPage.waitForLoading();
            Assert.assertTrue(emailPage.makeCellEmail(editedEmail));
        }

        emailPage.waitForLoading();
        emailPage.clickOnSortLink();
        emailPage.checkSortDirection(SortDirections.down);
        List<String> actualListEmailsColumnDown = emailPage.getTextFromEmailColumn(email.toString());
        List<String> expectedListEmailsColumnDown = new ArrayList<>(actualListEmailsColumnDown);
        Collections.sort(expectedListEmailsColumnDown);
        Assert.assertEquals(actualListEmailsColumnDown, expectedListEmailsColumnDown, "Emails are not sorted");

        emailPage.waitForLoading();
        emailPage.clickOnSortLink();
        emailPage.checkSortDirection(SortDirections.up);
        List<String> actualListEmailsColumnUp = emailPage.getTextFromEmailColumn(email.toString());
        List<String> expectedListEmailsColumnUp = new ArrayList<>(actualListEmailsColumnUp);
        Collections.sort(expectedListEmailsColumnUp);
        Assert.assertEquals(actualListEmailsColumnUp, expectedListEmailsColumnUp, "Emails are not sorted");

        for (String emailToDelete : editedEmails) {
            emailPage.openDropdown(emailToDelete);
            emailPage.waitForLoading();
            emailPage.deleteEmail();
            email = new Email();
            JsonPath actualDeletedEmail = email.getEmail(500, emailId).jsonPath();
            Assert.assertEquals(actualDeletedEmail.getString("message"), "Error! This email doesn't exist in our DB");
        }
        contact.deleteContact(200, contactId);
        JsonPath actualDeletedContact = contact.getContact(500, contactId).jsonPath();
        Assert.assertEquals(actualDeletedContact.getString("message"), "Error! This contact doesn't exist in our DB");
    }
}