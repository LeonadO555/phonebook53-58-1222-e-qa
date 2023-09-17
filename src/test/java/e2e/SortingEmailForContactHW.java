package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactPage;
import e2e.pages.EmailPage;
import e2e.pages.LoginPage;
import enums.ContactTabs;
import enums.SortDirection;
import enums.UserCredentials;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingEmailForContactHW extends TestBase {

    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;

    ContactInfoPage contactInfoPage;

    EmailPage emailPage;

    Faker faker = new Faker();

    @Test
    public void sortingEmailForContactHW() throws InterruptedException {

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
        emailPage.clickOnEmailsTab(ContactTabs.EMAILS);
        List<String> emailsList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String contactEmail = faker.internet().emailAddress();
            emailsList.add(contactEmail);
            emailPage.openAddDialog();
            emailPage.setEmail(contactEmail);
            emailPage.clickSaveButton();
            Thread.sleep(1000);
            emailPage.waitForEmailPage();
            Assert.assertTrue(emailPage.makeCellEmail(contactEmail));
        }

        emailPage.clickOnSortEmailLink();
        emailPage.checkSortDirection(SortDirection.UP);
        List<String> actualListEmailUp = emailPage.getTextFromEmailCell();
        Collections.sort(emailsList, Comparator.reverseOrder());
        Assert.assertEquals(actualListEmailUp, emailsList, "Actual list of emails is not equals expected");

        emailPage.clickOnSortEmailLink();
        List<String> actualListEmailDown = emailPage.getTextFromEmailCell();
        Collections.sort(emailsList, Comparator.naturalOrder());
        Assert.assertEquals(actualListEmailDown, emailsList, "Actual list of emails is not equals expected");

        contact.deleteContact(200, contactId);
        JsonPath actualDeletedContact = contact.getContact(500, contactId).jsonPath();
        Assert.assertEquals(actualDeletedContact.getString("message"), "Error! This contact doesn't exist in our DB");
    }
}
