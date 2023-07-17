package api.email;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.EmailDto;


public class Email extends ApiBase {

    Response response;

    EmailDto dto;

    Faker faker = new Faker();

    String newEmail = faker.internet().emailAddress();

    public EmailDto dataForCreateEmail(int contactId) {
        dto = new EmailDto();
        dto.setEmail(newEmail);
        dto.setContactId(contactId);
        return dto;
    }

    public EmailDto dataForEditEmail(int id, int contactId) {
        dto = new EmailDto();
        dto.setId(id);
        dto.setEmail(newEmail);
        dto.setContactId(contactId);
        return dto;
    }

    public Response createEmail(Integer code, int id) {
        String endpoint = "/api/email";
        response = postRequest(endpoint, code, dataForCreateEmail(id));
        return response;
    }

    public void editEmail(Integer code, int id, int contactId) {
        String endPoint = "/api/email";
        putRequest(endPoint, code, dataForEditEmail(id, contactId));
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

