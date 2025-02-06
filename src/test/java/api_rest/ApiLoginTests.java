package api_rest;

import config.AuthenticationController;
import dto.ErrorMessageDto;
import dto.UserDto;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiLoginTests extends AuthenticationController implements BaseApi {

    @Test
    public void loginPositiveTest() {
        UserDto user = UserDto.builder()
                .username("3mhulpw@gmail.com")
                .password("Pass123!")
                .build();
        Response response = requestRegLogin(user, LOGIN );
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void loginNegativeTest_wrongEmail() {
        UserDto user = UserDto.builder()
                .username("3mhulpw@gmail")
                .password("Pass123!")
                .build();
        Response response = requestRegLogin(user, LOGIN );
        System.out.println(response.getStatusCode());
 //       Assert.assertEquals(response.getStatusCode(), 200);
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        System.err.println(errorMessageDto);
        Assert.assertTrue(errorMessageDto.getMessage().toString().contains("Login or Password incorrect"));
    }
}
