package e2e;

import api.contact.Contact;
import org.testng.annotations.Test;

public class UserCanCreateEditDeleteContactViaApiTest {
    Contact contact;
    @Test
    public void userCanCreateEditDeleteContactViaApiTest(){
        contact = new Contact();
        contact.createContact(201);
    }
}
