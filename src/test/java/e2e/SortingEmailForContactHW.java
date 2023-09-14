package e2e;

import api.contact.Contact;
import com.github.javafaker.Faker;
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
import java.util.List;

public class SortingEmailForContactHW extends TestBase {

    Contact contact;
    LoginPage loginPage;
    ContactPage contactPage;

    EmailPage emailPage;

    Faker faker = new Faker();

    @Test
    public void sortingEmailForContactHW() {

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmSuccessfulLogin();

        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int id = createdContact.getInt("id");

        contactPage = new ContactPage(app.driver);
        contactPage.waitForLoading();
        contactPage.openContactById(String.valueOf(id));
        
        emailPage = new EmailPage(app.driver);
        emailPage.clickOnEmailsTab(ContactTabs.EMAILS);
        for (int i = 0; i < 3; i++) {
            String contactEmail = faker.internet().emailAddress();
            emailPage.openAddDialog();
            emailPage.waitForLoading();
            emailPage.setEmail(contactEmail);
            emailPage.clickSaveButton();
//            emailPage.handleSuccessfulToast();
            emailPage.waitForEmailPage();
            Assert.assertTrue(emailPage.makeCellEmail(contactEmail));
            emailPage.waitForEmailPage();
        }

        List<String> expectedListEmailsUp = new ArrayList<>();
        expectedListEmailsUp.add("wiliam@gmail.com");
        expectedListEmailsUp.add("peter@meta.net");
        expectedListEmailsUp.add("mary@hotline.com");
        expectedListEmailsUp.add("arthur@barmer.net");


        List<String> expectedListEmailsDown = new ArrayList<>();
        expectedListEmailsDown.add("arthur@barmer.net");
        expectedListEmailsDown.add("mary@hotline.com");
        expectedListEmailsDown.add("peter@meta.net");
        expectedListEmailsDown.add("wiliam@gmail.com");


        emailPage.clickOnSortEmailLink();
        emailPage.checkSortDirection(SortDirection.UP);
        List<String> actualListEmailUp = emailPage.getTextFromEmailCell();
        Assert.assertEquals(actualListEmailUp, expectedListEmailsUp, "Actual list of emails is not equals expected");


        emailPage.clickOnSortEmailLink();
        emailPage.checkSortDirection(SortDirection.DOWN);
        List<String> actualListEmailDown = emailPage.getTextFromEmailCell();
        Assert.assertEquals(actualListEmailDown, expectedListEmailsDown, "Actual list of emails is not equals expected");

        contact.deleteContact(200, id);
        JsonPath actualDeletedContact = contact.getContact(500, id).jsonPath();
        Assert.assertEquals(actualDeletedContact.getString("message"), "Error! This contact doesn't exist in our DB");
    }
}
