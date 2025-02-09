package api_rest;

import config.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.TokenDto;
import dto.UserDto;
import helper.BaseApi;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestNGListener;

import static utils.RandomUtils_lvl.*;

@Listeners(TestNGListener.class)
public class ApiRegistrationTests extends AuthenticationController implements BaseApi {
    Logger logger = LoggerFactory.getLogger(TestNGListener.class);


    @Test
    public void registrationPositive_rest_assured_Test() {
        TokenDto tokenDto = null;
        UserDto userDto = UserDto.builder()
                .firstName("T" + generateStringTextOnly_stream(4))
                .lastName("T" + generateStringTextOnly(9))
                .username(generateEmail(5))
                .password("Qwerty123!")
                .build();
        Response response = requestRegLogin(userDto, REGISTRATION);
        if (response.getStatusCode() == 200) {
            tokenDto = response.getBody().as(TokenDto.class);
        } else {
            Assert.fail("Registration went wrong " + response.getStatusCode());
        }
        //       System.out.println(userDto + " is registered");
        logger.info("\n{} is registered", userDto.toString());
        Assert.assertNotNull(tokenDto);
    }

    @Test
    public void registrationNegative_rest_assured_Test_wrongEmail() {
        ErrorMessageDtoString errorMessage;
        UserDto userDto = UserDto.builder()
                .firstName("T" + generateStringTextOnly_stream(4))
                .lastName("T" + generateStringTextOnly(9))
                .username(generateString(15))
                .password("Qwerty123!")
                .build();
        Response response = requestRegLogin(userDto, REGISTRATION);
        if (response.getStatusCode() == 400) {
            errorMessage = response.getBody().as(ErrorMessageDtoString.class);
            softAssert.assertEquals(errorMessage.getError(), "Bad Request",
                    "Error is not Bad Request");
            String message = errorMessage.getMessage().toString();
            softAssert.assertTrue((message.contains("username")
                            && message.contains("must be a well-formed email address")),
                    "error message is not about wrong Email: " + message);
        } else {
            Assert.fail("not negative Registration and status in response is " + response.getStatusCode());
        }
        softAssert.assertAll();
    }

    @Test
    public void registrationNegative_rest_assured_Test_wrongPassword() {
        ErrorMessageDtoString errorMessage;
        UserDto userDto = UserDto.builder()
                .firstName("T" + generateStringTextOnly_stream(4))
                .lastName("T" + generateStringTextOnly(9))
                .username(generateEmail(7))
                .password("Qwerty12")
                .build();
        Response response = requestRegLogin(userDto, REGISTRATION);
        if (response.getStatusCode() == 400) {
            errorMessage = response.getBody().as(ErrorMessageDtoString.class);
            softAssert.assertEquals(errorMessage.getError(), "Bad Request",
                    "Error is not Bad Request");
            String message = errorMessage.getMessage().toString();
            softAssert.assertTrue((message.contains("password")
                            && message.contains("and 1 number; Can contain special characters")),
                    "error message is not about password: " + message);
        } else {
            Assert.fail("not a negative Registration or status in response is " + response.getStatusCode());
        }
        softAssert.assertAll();
    }


}


