package api.email;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.EmailDto;

public class email extends ApiBase {
    Response response;
    EmailDto dto;
    Faker faker = new Faker();
    String email = faker.internet().emailAddress();

    public EmailDto randomDataForCreateEmail(int contactId) {
        dto = new EmailDto();
        dto.setEmail(email);
        dto.setContactId(contactId);
        return dto;

    }

    public EmailDto randomDataForEditEmail(int Id, int contactId) {
        dto = new EmailDto();
        dto.setId(contactId);
        dto.setEmail(email);
        dto.setContactId(contactId);
        return dto;
    }

    public Response createEmail(Integer code, int id) {
        String endPoint = "/api/email";
        response = postRequest(endPoint, code, randomDataForCreateEmail(id));
        return response;
    }

    public Response editEmail(Integer code, int id, int contactId) {
        String endPoint = "/api/email";
        response = postRequest(endPoint, code, randomDataForEditEmail(id, contactId));
        return response;
    }

    public Response deleteEmail(Integer code, int id) {
        String endPoint = "/api/email/{id}";
        response = deleteRequest(endPoint, code, id);
        return response;
    }

    public Response getEmail(Integer code, Integer id) {
        String endPoint = "/api/adress/{id}";
        response = getRequestWithParam(endPoint, code, "id", id);
        return response;
    }

    public Response getsEmails(Integer code, Integer id) {
        String endPoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endPoint, code, "contactId", id);
        return response;
    }
}
