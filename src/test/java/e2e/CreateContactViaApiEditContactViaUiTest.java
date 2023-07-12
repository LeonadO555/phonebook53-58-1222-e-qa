package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.LoginPage;
import enums.ContactButtons;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateContactViaApiEditContactViaUiTest extends TestBase {
    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;
    ContactInfoPage contactInfoPage;
    Faker faker = new Faker();

    @Test
    public void createContactViaApiEditContactViaUiTest() {
        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.internet().uuid();

        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int id = createdContact.getInt("id");

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
        contactInfoPage.setlEditForm(editFirstName, editLastName, editDescription);
        contactInfoPage.saveChanges();
        contactInfoPage.handleSuccessfulToast();
        boolean visibleStatus = contactInfoPage.saveChanges();
        Assert.assertFalse(visibleStatus, "Save button is visible");
        List<String> actualEditedContact = contactInfoPage.getEditForm();
        List<String> expectedEditedContact = new ArrayList<>();
        expectedEditedContact.add(editFirstName);
        expectedEditedContact.add(editLastName);
        expectedEditedContact.add(editDescription);
        Assert.assertEquals(actualEditedContact, expectedEditedContact);
    }
}
