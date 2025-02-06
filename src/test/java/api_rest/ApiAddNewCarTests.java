package api_rest;

import config.CarControler;
import dto.CarDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.RandomUtils.generatePhone;

public class ApiAddNewCarTests extends CarControler  {

    @Test
    public void addNewCarPositiveTest(){
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel("Gas")
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
        login();
        Response response = addUserCar(tokenDto.getAccessToken(), car);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
