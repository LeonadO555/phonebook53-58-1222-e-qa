package e2e;

import api.contact.Contact;
import api.email.Email;
import io.restassured.path.json.JsonPath;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class UserCanWorkWithEmailInNewContact {
    Contact contact;
    Email email;

    @Test
    public void userCanWorkWithEmailInNewContact() throws JSONException {
        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int contactId = createdContact.getInt("id");

        email = new Email();
        email.createEmail(201, contactId);
        JsonPath createdEmail = email.getEmail(200, contactId).jsonPath();
        int emailId = createdEmail.getInt("[0].id");
        String email = createdEmail.getString("[0].email");

        JSONObject actualEmailJson = new JSONObject();
        actualEmailJson.put("id", emailId);
        actualEmailJson.put("email", email);
        actualEmailJson.put("contactId", contactId);

        String actualJson = actualEmailJson.toString();
        String expectedJson = "{\n" +
                "        \"id\": " + emailId + ",\n" +
                "        \"email\": \"valid.email@gamil.moc\",\n" +
                "        \"contactId\": " + contactId + "\n" +
                "    }";
        JSONAssert.assertEquals(actualJson, expectedJson, JSONCompareMode.STRICT);

//        int id = emailId;
//        email.editEmail(200, id, contactId);
//        JsonPath editedAddress = email.getEmail(200, id).jsonPath();
//        LinkedHashMap<String, String> objectEditedAddress = new LinkedHashMap<>();
//        objectEditedAddress.put(editedAddress.getString("email"), email.dataForEditEmail(id, contactId).getEmail());
//
//
//        for (Map.Entry<String, String> object : objectEditedAddress.entrySet()) {
//            String actualResult = object.getKey();
//            String expectedResult = object.getValue();
//            Assert.assertEquals(actualResult, expectedResult, actualResult + "not equal" + expectedResult);
//        }
//
//
//        email.deleteEmail(200, id);
//        JsonPath actualDeleteAddress = email.getEmail(500, id).jsonPath();
//        Assert.assertEquals(actualDeleteAddress.getString("message"), "Error! This email doesn't exist in our DB");

    }
}
