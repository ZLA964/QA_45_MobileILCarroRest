package ui_mobile;

import config.AppiumConfig;
import config.CarControler;
import dto.CarsDto;
import dto.UserDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class DelateCarTests extends AppiumConfig {

    SearchScreen searchScreen;
    LoginScreen loginScreen;
    MyCarsScreen myCarsScreen;

    @BeforeMethod
    public void login() {
        new SplashScreen(driver);
        searchScreen = new SearchScreen(driver);
        searchScreen.goToLoginScreen();
        loginScreen = new LoginScreen(driver);
        loginScreen.login(UserDto.builder()
                .username("3mhulpw@gmail.com")
                .password("Pass123!")
                .build());
        searchScreen.goToMyCarsScreen();
    }

    @Test
    public void deleteFirstCarPositiveTest(){
        CarControler carControler = new CarControler();
        carControler.login();
        Response responseBefore = carControler.getUserCar(carControler.tokenDto.getAccessToken());
        int quantityBeforeDelete = responseBefore.body().as(CarsDto.class).getCars().length;
        System.out.println(quantityBeforeDelete);
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.deleteFirstCar();
        Response responseAfter = carControler.getUserCar(carControler.tokenDto.getAccessToken());
        int quantityAfterDelete = responseAfter.body().as(CarsDto.class).getCars().length;
        System.out.println(quantityBeforeDelete + " X " + quantityAfterDelete);
        Assert.assertEquals(quantityBeforeDelete-1, quantityAfterDelete);

    }
}
