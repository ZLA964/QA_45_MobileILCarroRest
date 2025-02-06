package api_rest;

import config.CarControler;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ApiGetUserCarsTests extends CarControler implements BaseApi {

    @Test
    public  void getUserCarsPositiveTest(){
        login();
        Response response = getUserCar(tokenDto.getAccessToken());
        System.out.println("--> "+ response.getStatusCode());
    }

}
