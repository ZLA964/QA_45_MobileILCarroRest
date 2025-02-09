package config;

import dto.CarDto;
import dto.SearchDto;
import dto.TokenDto;
import dto.UserDto;
import helper.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CarControler implements BaseApi {
    public TokenDto tokenDto;

    //    @BeforeSuite
    public void login(){
        UserDto user = UserDto.builder()
                .username("3mhulpw@gmail.com")
                .password("Pass123!")
                .build();
        AuthenticationController authenticationController = new AuthenticationController();
        Response response = authenticationController.requestRegLogin(user, LOGIN);
        if (response.getStatusCode() == 200) {
            tokenDto = response.getBody().as(TokenDto.class);
        } else
            System.out.println("Something went wrong " + response.getStatusCode());
    }

    public Response searchCar(SearchDto search) {
        return given()
                .contentType(ContentType.JSON)
                .body(search)
                .post(BASE_URL + SEARCH)
                .thenReturn();
    }

    public Response searchCar_wrongURL(SearchDto search) {
        return given()
                .contentType(ContentType.JSON)
                .body(search)
                .post(BASE_URL + SEARCH + "a")
                .thenReturn();
    }

    public Response getUserCar(String token) {
        return given()
                .header(AUTH, token)
                .when().get(BASE_URL + GET_USER_CARS)
                .thenReturn();
    }

    public Response addUserCar(String token, CarDto car) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(car)
                .when().post(BASE_URL + ADD_NEW_CAR)
                .thenReturn();
    }

    public Response deleteCarWithSernum(String token, String serNum) {
        return given()
                .header("Authorization", token)
                .when().delete(BASE_URL + DELETE_CAR + serNum)
                .thenReturn();
    }
}



