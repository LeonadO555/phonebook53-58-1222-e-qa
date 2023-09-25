package api.phone;

import api.ApiBase;
import io.restassured.response.Response;

public class Phone extends ApiBase {

    Response response;

    public Response deletePhone(Integer code, int id) {
        String endPoint = "/api/phone/{id}";
        response = deleteRequest(endPoint, code, id);
        return response;
    }

    public Response getAllPhones(Integer code, Integer id) {
        String endPoint = "/api/phone/{contactId}/all";
        response = getRequestWithParam(endPoint, code, "contactId", id);
        return response;
    }


    // data for test using faker

    //    PhoneDto dto;

    //    Faker faker = new Faker();

//    String countryCode = faker.country().countryCode2();
//    String phoneNumber = faker.phoneNumber().phoneNumber();
//
//    public PhoneDto randomDataForCreatePhone(int contactId) {
//        dto = new PhoneDto();
//        dto.setCountryCode(faker.country().countryCode2());
//        dto.setPhoneNumber(faker.phoneNumber().phoneNumber());
//        dto.setContactId(contactId);
//        return dto;
//    }
//
//    public PhoneDto dataForEditPhone(int id, int contactId) {
//        dto = new PhoneDto();
//        dto.setId(id);
//        dto.setCountryCode(faker.country().countryCode2());
//        dto.setPhoneNumber(faker.phoneNumber().phoneNumber());
//        dto.setContactId(contactId);
//        return dto;
//    }

}
