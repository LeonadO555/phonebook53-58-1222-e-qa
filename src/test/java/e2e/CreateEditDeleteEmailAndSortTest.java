package e2e;

import api.contact.Contact;
import api.email.Email;
import com.github.javafaker.Faker;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.EmailPage;
import e2e.pages.LoginPage;
import enums.UserCredentials;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEditDeleteEmailAndSortTest extends TestBase {

    LoginPage loginPage;
    Email email;
    EmailPage emailPage;
    Contact contact;
    ContactPage contactPage;
    ContactInfoPage contactInfoPage;
    Faker faker = new Faker();

    @Test
    public void createEditDeleteEmailAndSortTest() {
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
        String[] createdEmails = new String[3]; // Создаем массив для хранения сгенерированных email

        for (int i = 0; i < 3; i++) {
            emailPage.openAddDialog();
            createdEmails[i] = faker.internet().emailAddress(); // Сохраняем сгенерированный email в массив
            emailPage.setEmail(createdEmails[i]);
            emailPage.clickSaveButton();
            Assert.assertTrue(emailPage.makeCellEmail(createdEmails[i]));
        }

        for (String email : createdEmails) {
            Assert.assertTrue(emailPage.makeCellEmail(email));
        }

        email = new Email();
        JsonPath createdEmail = email.getEmails(200, contactId).jsonPath();
        int emailId = createdEmail.getInt("[0].id");

// Открываем выпадающее меню и редактируем email
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
