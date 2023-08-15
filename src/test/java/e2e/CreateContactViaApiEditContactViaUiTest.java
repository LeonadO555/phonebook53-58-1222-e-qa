package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
import e2e.pages.ContactPage;
import e2e.pages.LoginPage;
import enums.UserCredentials;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;


public class CreateContactViaApiEditContactViaUiTest extends TestBase {
    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;

    @Test
    public void createContactViaApiEditContactViaUiTest() throws IOException {
        Faker faker = new Faker();
        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.internet().uuid();


        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int id = createdContact.getInt("id");
        logger.debug(String.valueOf(id));

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.takeScreenshotLoginButton();
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.login();
        loginPage.confirmSuccessfulLogin();

        contactPage = new ContactPage(app.driver);
        contactPage.waitForLoading();
        contactPage.openContactById(String.valueOf(id));

        
    }
}
