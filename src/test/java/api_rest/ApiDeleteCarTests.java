package api_rest;

import config.CarControler;
import dto.CarDto;
import dto.CarsDto;
import dto.ErrorMessageDtoString;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static helper.RandomUtils.*;

public class ApiDeleteCarTests extends CarControler implements BaseApi {
    CarDto[] cars;

    private void gelListCars(){
        cars = null;
        Response response = getUserCar(tokenDto.getAccessToken());
        cars = response.getBody().as(CarsDto.class).getCars();
    }

    @Test
    public void deleteRandomCarPositiveTest(){
        login();
        gelListCars();
        int quantityCarsBefore = cars.length;
        int index = getRandomIndex(quantityCarsBefore);
        CarDto carRandom = cars[index];
        String serNum = carRandom.getSerialNumber();
        System.out.println("serNum -> "+ serNum);
        System.out.println("quantityCarsBefore -> " + quantityCarsBefore);
        Response response = deleteCarWithSernum(tokenDto.getAccessToken(), serNum);
        softAssert.assertEquals(response.statusCode(), 200);
        if(response.statusCode() == 200){
            gelListCars();
            int quantityCarsAfter = cars.length;
            System.out.println("quantityCarsAfter -> " + quantityCarsAfter);
            softAssert.assertEquals(quantityCarsBefore-quantityCarsAfter, 1);
            CarDto carForDelete = Arrays.stream(cars)
                    .filter(car -> car.equals(carRandom))
                    .findFirst()
                    .orElse(null);
            System.out.println("carForDelete -> " + carForDelete);
            softAssert.assertNull(carForDelete);
        } else {
            Assert.fail("Car not deleted " + response.getStatusCode());
        }
        softAssert.assertAll();
    }

    @Test
    public void deleteRandomCarNegativeTest_wrongSerNum(){
        ErrorMessageDtoString errorMessage;
        login();
        gelListCars();
        int quantityCarsBefore = cars.length;
        int index = getRandomIndex(quantityCarsBefore);
        CarDto carRandom = cars[index];
        String serNum = carRandom.getSerialNumber();
        System.out.println("serNum -> "+ serNum);
        System.out.println("quantityCarsBefore -> " + quantityCarsBefore);
        Response response = deleteCarWithSernum(tokenDto.getAccessToken(), "serNum");
        System.out.println("code -> "+ response.statusCode() );
        softAssert.assertNotEquals(response.statusCode(), 200);
        if(response.statusCode() == 400){
            gelListCars();
            int quantityCarsAfter = cars.length;
            System.out.println("quantityCarsAfter -> " + quantityCarsAfter);
            softAssert.assertEquals(quantityCarsBefore-quantityCarsAfter, 0);
            CarDto carForDelete = Arrays.stream(cars)
                    .filter(car -> car.equals(carRandom))
                    .findFirst()
                    .orElse(null);
//            System.out.println("carForDelete -> " + carForDelete);
            softAssert.assertNotNull(carForDelete);
            errorMessage = response.getBody().as(ErrorMessageDtoString.class);
            softAssert.assertEquals(errorMessage.getError(), "Bad Request",
                    "Error is not Bad Request");
            String message = errorMessage.getMessage().toString();
            System.out.println(message);
            softAssert.assertTrue((message.contains("Car with serial number serNum not found")),
                    "error message is not about wrong serial number: " + message);
        } else {
            gelListCars();
            int quantityCarsAfter = cars.length;
            System.out.println("quantityCarsAfter -> " + quantityCarsAfter);
            Assert.fail("Car deleted or response status is not 400" + response.getStatusCode());
        }
        softAssert.assertAll();
    }
}
