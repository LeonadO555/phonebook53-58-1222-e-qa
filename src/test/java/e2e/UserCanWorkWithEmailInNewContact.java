package e2e;

import api.contact.Contact;
import api.email.Email;
import io.restassured.path.json.JsonPath;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserCanWorkWithEmailInNewContact {

    Contact contact;
    Email email;

    @Test
    public void userCanWorkWithEmailInNewContact() throws JSONException, JSONException {
        contact = new Contact();
        //create new contact - POST-запрос
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int contactId = createdContact.getInt("id");
        email = new Email();
        email.createEmail(201, contactId);
        JsonPath createdEmail = email.getEmails(200, contactId).jsonPath();
        int emailId = createdEmail.getInt("[0].id");
        String enteredEmail = createdEmail.getString("[0].email");

        JSONObject actualEmailJson = new JSONObject();
        actualEmailJson.put("id", emailId);
        actualEmailJson.put("email", enteredEmail);
        actualEmailJson.put("contactId", contactId);

        String actualJson = actualEmailJson.toString();
        String expectedJson = "{\n" +
                "        \"id\": " + emailId + ",\n" +
                "        \"email\": " + enteredEmail + ",\n" +
                "        \"contactId\": " + contactId + "\n" +
                "    }";
        JSONAssert.assertEquals(actualJson, expectedJson, JSONCompareMode.STRICT);

        email.editEmail(200, emailId, contactId);
        JsonPath editedEmail = email.getEmail(200, emailId).jsonPath();

        LinkedHashMap<String, String> objectEditedData = new LinkedHashMap<>();
        objectEditedData.put(editedEmail.getString("email"), email.dataForEditEmail(emailId, contactId).getEmail());


        for (Map.Entry<String, String> object : objectEditedData.entrySet()) {
            String actualResult = object.getKey();
            String expectedResult = object.getValue();
            Assert.assertEquals(actualResult, expectedResult, actualResult + "not equals" + expectedResult);
        }

        email.deleteEmail(200, emailId);

        JsonPath actualDeletedEmail = email.getEmail(500, emailId).jsonPath();
        Assert.assertEquals(actualDeletedEmail.getString("message"), "Error! This email doesn't exist in our DB");

        contact.deleteContact(200, contactId);
        JsonPath actualDeletedContact = contact.getContact(500, contactId).jsonPath();
        Assert.assertEquals(actualDeletedContact.getString("message"), "Error! This contact doesn't exist in our DB");
    }
}
