package api.address;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.AddressDto;

public class Address extends ApiBase {
    Response response;
    AddressDto dto;
    Faker faker = new Faker();

    public AddressDto randomDataForCreateAddress(int contactId){
        dto = new AddressDto();
        dto.setCity("Berlin");
        dto.setCountry("Germany");
        dto.setStreet("Lenin");
        dto.setZip("12345");
        dto.setContactId(contactId);
        return dto;
    }

    public AddressDto dataForEditAddress(int id, int contactId){
        dto = new AddressDto();
        dto.setId(id);
        dto.setCity("Moscow");
        dto.setCountry("Russia");
        dto.setStreet("Pushkin");
        dto.setZip("199555");
        dto.setContactId(contactId);
        return dto;
    }

    public Response createAddress(Integer code, int id){
        String endPoint = "/api/address";
        response = postRequest(endPoint,code, randomDataForCreateAddress(id));
        return response;
    }

    public void editAddress(Integer code, int id, int contactId){
        String endPoint = "/api/address";
        putRequest(endPoint,code,dataForEditAddress(id, contactId));
    }

    public Response deleteAddress(Integer code, int id){
        String endPoint = "/api/address/{id}";
        response = deleteRequest(endPoint,code,id);
        return response;
    }

    public Response getAddress(Integer code, Integer id){
        String endPoint = "/api/address/{id}";
        response = getRequestWithParam(endPoint,code,"id",id);
        return response;
    }

    public Response getAddresses(Integer code, Integer id){
        String endPoint = "/api/address/{contactId}/all";
        response = getRequestWithParam(endPoint,code,"contactId",id);
        return response;
    }

}