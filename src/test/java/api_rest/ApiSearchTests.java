package api_rest;

import config.CarControler;
import dto.CarsDto;

import dto.SearchDto;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ApiSearchTests extends CarControler implements BaseApi {

    @Test
    public void searchPositiveTest() {
        CarsDto cars;
        SearchDto search = SearchDto.builder()
                .city("Rehovot")
                .startDate(
                        LocalDate.now().plusDays(3).plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(
                        LocalDate.now().plusDays(10).plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        System.out.println(search);
        Response response = searchCar(search);
        int code = response.statusCode();
        softAssert.assertEquals(code, 200);
        if (code == 200) {
            cars = response.getBody().as(CarsDto.class);
            softAssert.assertNotNull(cars);
            System.out.println("Found " + cars.getCars().length + " cars");
        } else {
            Assert.fail("Something went wrong " + response.getStatusCode());
        }
        softAssert.assertAll();
    }

    @Test
    public void searchNegativeTest_wrongCity() {   // Bag ? ?? !
        CarsDto cars;
        SearchDto search = SearchDto.builder()
                //         .city("Haifa")
                .startDate(
                        LocalDate.now().plusDays(3).plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(
                        LocalDate.now().plusDays(10).plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        System.out.println(search);
        Response response = searchCar(search);
        int code = response.statusCode();
        System.out.println(code);
        softAssert.assertNotEquals(code, 200);
        if (code != 200) {
            System.out.println("all right");
// no for assert !!!
        } else {
            cars = response.getBody().as(CarsDto.class);
            softAssert.assertNotNull(cars);
            System.out.println("Found " + cars.getCars().length + " cars");
            Assert.fail("Something went wrong " + response.getStatusCode());
        }
        softAssert.assertAll();
    }

    @Test
    public void searchNegativeTest_wrongURL() {
        CarsDto cars;
        SearchDto search = SearchDto.builder()
                .city("Rexovot")
                .startDate(
                        LocalDate.now().plusDays(3).plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(
                        LocalDate.now().plusDays(10).plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        System.out.println(search);
        Response response = searchCar_wrongURL(search);
        int code = response.statusCode();
        softAssert.assertNotEquals(code, 200);
        if (code != 200) {
            System.out.println("Response code -> " + code);
            softAssert.assertEquals(code, 403);
            //   ErrorMessageDto error = response.getBody().as(ErrorMessageDto.class);
            if (response.getBody() != null && !response.getBody().asString().isEmpty()) {
                System.out.println("Body: " + response.getBody().asString());
            } else {
                System.out.println("No body in response");
                softAssert.assertTrue(response.getBody() == null || response.getBody().asString().isEmpty());
            }
        } else {
            cars = response.getBody().as(CarsDto.class);
            System.out.println("Found " + cars.getCars().length + " cars");
            Assert.fail("Something went wrong " + response.getStatusCode());
        }
        softAssert.assertAll();
    }


    @Test
    public void searchNegativeTest_wrongData() {   // Bag ? ?? !
        CarsDto cars;
        SearchDto search = SearchDto.builder()
                .city("Haifa")
                .startDate(
                        LocalDate.now().plusDays(3).plusMonths(-1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(
                        LocalDate.now().plusDays(10).plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        System.out.println(search);
        Response response = searchCar(search);
        int code = response.statusCode();
        System.out.println(code);
        softAssert.assertNotEquals(code, 200);
        if (code != 200) {
            System.out.println("all right");
// no for assert !!!
        } else {
            cars = response.getBody().as(CarsDto.class);
            softAssert.assertNotNull(cars);
            System.out.println("Found " + cars.getCars().length + " cars");
            Assert.fail("Something went wrong " + response.getStatusCode());
        }
        softAssert.assertAll();
    }

    @Test
    public void searchNegativeTest_wrongData2() {   // Bag ? ?? !
        CarsDto cars;
        SearchDto search = SearchDto.builder()
                .city("Haifa")
                .startDate(
                        LocalDate.now().plusDays(3).plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(
                        LocalDate.now().plusDays(10).plusMonths(-1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        System.out.println(search);
        Response response = searchCar(search);
        int code = response.statusCode();
        System.out.println(code);
        softAssert.assertNotEquals(code, 200);
        if (code != 200) {
            System.out.println("all right");
// no for assert !!!
        } else {
            cars = response.getBody().as(CarsDto.class);
            softAssert.assertNotNull(cars);
            System.out.println("Found " + cars.getCars().length + " cars");
            Assert.fail("Something went wrong " + response.getStatusCode());
        }
        softAssert.assertAll();
    }

}
