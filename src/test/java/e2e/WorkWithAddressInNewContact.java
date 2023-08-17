package e2e;

import api.address.Address;
import api.contact.Contact;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class WorkWithAddressInNewContact {

    Contact contact;
    Address address;

    @Test(description = "Work with address in new contact")
    @Feature("Contact")
    @Story("Addresses api")
    @Severity(SeverityLevel.NORMAL)

    public void userCanWorkWithAddressInNewContact() throws JSONException {
        contact = new Contact();
        JsonPath createdContact = contact.createContact(201).jsonPath();
        int contactId = createdContact.getInt("id");

        address = new Address();
        address.createAddress(201, contactId);
        JsonPath createdAddress = address.getAddresses(200, contactId).jsonPath();
        int addressId = createdAddress.getInt("[0].id");

        Object actualAddress = createdAddress.getJsonObject("[0]");
        String actualJson = actualAddress.toString();

        String expectedJson = "{\n" +
                "        \"id\": " + addressId + ",\n" +
                "        \"city\": \"Berlin\",\n" +
                "        \"country\": \"Germany\",\n" +
                "        \"street\": \"Lenin\",\n" +
                "        \"zip\": 12345,\n" +
                "        \"contactId\": " + contactId + "\n" +
                "    }";
        JSONAssert.assertEquals(actualJson, expectedJson, JSONCompareMode.STRICT);
    }
}
