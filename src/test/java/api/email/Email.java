package api.email;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.EmailDto;

public class Email extends ApiBase {

    Response response;

    EmailDto dto;

    Faker faker = new Faker();

    private String consistentEmail = faker.internet().emailAddress();

    public EmailDto dataForCreateEmail(int contactId) {
        dto = new EmailDto();
        dto.setEmail(consistentEmail);
        dto.setContactId(contactId);
        return dto;
    }

    public EmailDto dataForEditEmail(int id, int contactId) {
        dto = new EmailDto();
        dto.setId(id);
        dto.setEmail(consistentEmail); // Use the same consistent email for editing
        dto.setContactId(contactId);
        return dto;
    }


    public Response createEmail(Integer code, int id) {
        String endpoint = "/api/email";
        dto = dataForCreateEmail(id);
        response = postRequest(endpoint, code, dto);
        return response;
    }

    public Response editEmail(Integer code, int id, int contactId) {
        String endPoint = "/api/email";
        dto = dataForEditEmail(id, contactId);
        response = putRequest(endPoint, code, dto);
        return response;
    }

    public Response deleteEmail(Integer code, int id) {
        String endPoint = "/api/email/{id}";
        response = deleteRequest(endPoint, code, id);
        return response;
    }

    public Response getEmail(Integer code, Integer id) {
        String endPoint = "/api/email/{id}";
        response = getRequestWithParam(endPoint, code, "id", id);
        return response;
    }

    public Response getEmails(Integer code, Integer id) {
        String endPoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endPoint, code, "contactId", id);
        return response;
    }
}
