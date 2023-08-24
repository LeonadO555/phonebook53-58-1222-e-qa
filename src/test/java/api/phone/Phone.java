package api.phone;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.PhoneDto;

public class Phone extends ApiBase {
    Response response;
    PhoneDto dto;
    Faker faker = new Faker();
    String countryCode = faker.address().countryCode();
    String phoneNumber = faker.phoneNumber().phoneNumber();


    public PhoneDto randomDataForCreatePhone(int contactId) {
        dto = new PhoneDto();
        dto.setCountryCode(faker.address().countryCode());
        dto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        dto.setContactId(contactId);
        return dto;
    }

    public PhoneDto dataForEditPhone(int id, int contactId) {
        dto = new PhoneDto();
        dto.setId(id);
        dto.setCountryCode(faker.address().countryCode());
        dto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        dto.setContactId(contactId);
        return dto;
    }

    public Response createPhone(Integer code, int contactId) {
        String endPoint = "/api/phone";
        response = postRequest(endPoint, code, randomDataForCreatePhone(contactId));
        response.as(PhoneDto.class);
        return response;
    }

    public void editPhone(Integer code, int id, int contactId) {
        String endPoint = "/api/phone";
        putRequest(endPoint, code, dataForEditPhone(id, contactId));
    }

    public Response deletePhone(Integer code, int id) {
        String endPoint = "/api/phone/{id}";
        response = deleteRequest(endPoint, code, id);
        return response;
    }

    public Response getPhone(Integer code, Integer id) {
        String endPoint = "/api/phone/{id}";
        response = getRequestWithParam(endPoint, code, "id", id);
        return response;
    }

    public Response getPhones(Integer code, Integer id) {
        String endPoint = "/api/phone/{contactId}/all";
        response = getRequestWithParam(endPoint, code, "contactId", id);
        return response;
    }


}
