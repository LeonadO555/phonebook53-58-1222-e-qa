package api.contact;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.ContactDto;

public class Contact extends ApiBase {
    Response response;
    ContactDto dto;
    Faker faker = new Faker();

    public ContactDto generateRandomDataForCreateContact(){
        dto = new ContactDto();
        dto.setFirstName(faker.internet().uuid());
        dto.setLastName(faker.internet().uuid());
        dto.setDescription(faker.internet().uuid());
        return dto;
    }

    public Response createContact(Integer code){
        String endPoint = "/api/contact";
        response = postRequest(endPoint,code,generateRandomDataForCreateContact());
        response.as(ContactDto.class);
        return response;
    }
}
