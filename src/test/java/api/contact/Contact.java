package api.contact;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.ContactDto;

public class Contact extends ApiBase {
    Response response;
    ContactDto dto;
    Faker faker = new Faker();

    public ContactDto randomDataForCreateContact(){
        dto = new ContactDto();
        dto.setFirstName(faker.internet().uuid());
        dto.setLastName(faker.internet().uuid());
        dto.setDescription(faker.internet().uuid());
        return dto;
    }

    public ContactDto dataForEditContact(int id){
        dto = new ContactDto();
        dto.setId(id);
        dto.setFirstName("Leo");
        dto.setLastName("Mikhailov");
        dto.setDescription("I am a teacher");
        return dto;
    }

    public Response createContact(Integer code){
        String endPoint = "/api/contact";
        response = postRequest(endPoint,code, randomDataForCreateContact());
        response.as(ContactDto.class);
        return response;
    }

    public void editContact(Integer code, int id){
        String endPoint = "/api/contact";
        putRequest(endPoint,code,dataForEditContact(id));
    }

    public Response deleteContact(Integer code, int id){
        String endPoint = "/api/contact/{id}";
        response = deleteRequest(endPoint,code,id);
        return response;
    }

    public Response getContact(Integer code, Integer id){
        String endPoint = "/api/contact/{id}";
        response = getRequestWithParam(endPoint,code,"id",id);
        return response;
    }

}
