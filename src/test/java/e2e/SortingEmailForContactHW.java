package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
import e2e.pages.ContactPage;
import e2e.pages.EmailPage;
import e2e.pages.LoginPage;
import enums.ContactButtons;
import enums.ContactTabs;
import enums.UserCredentials;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SortingEmailForContactHW extends TestBase {

    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;

    EmailPage emailPage;

    Faker faker = new Faker();

    @Test
    public void sortingEmailForContactHW() throws IOException {
        String editedContactEmail = faker.internet().emailAddress();

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

        emailPage = new EmailPage(app.driver);
        emailPage.waitForLoading();
        emailPage.clickOnEmailsTab(ContactTabs.EMAILS);
        for (int i = 0; i < 3; i++) {
            String contactEmail = faker.internet().emailAddress();
            addEmail(contactEmail);
            Assert.assertTrue(emailPage.makeCellEmail(contactEmail));
        }
    }

    private void addEmail(String contactEmail) {
        emailPage.clickOnButton(ContactButtons.ADD_EMAIL);
        emailPage.waitForLoading();
        emailPage.setEmail(contactEmail);
        emailPage.clickSaveButton();
    }


}
