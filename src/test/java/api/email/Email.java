package api.email;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.EmailDto;

public class Email extends ApiBase {
    Response response;

    EmailDto dto;

    Faker faker = new Faker();

    public EmailDto randomDataForCreateEmail(int contactId) {
        dto = new EmailDto();
        dto.setEmail("valid.email@gamil.moc");
        dto.setContactId(contactId);
        return dto;
    }

    public EmailDto dataForEditEmail(int id, int contactId) {
        dto = new EmailDto();
        dto.setId(id);
        dto.setEmail("irasp.email@gamil.com");
        dto.setContactId(contactId);
        return dto;
    }

    public Response createEmail(Integer code, int id) {
        String endPoint = "/api/email";
        response = postRequest(endPoint, code, randomDataForCreateEmail(id));
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

