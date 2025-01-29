package ui_mobile;

import config.AppiumConfig;
import dto.CarDto;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helper.RandomUtils.*;

public class AddNewCarTests extends AppiumConfig {

    SearchScreen searchScreen;

    @BeforeMethod
    public void login(){
        new SplashScreen(driver);
        searchScreen = new SearchScreen(driver);
        searchScreen.goToLoginScreen();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.login(UserDto.builder()
                .username("3mhulpw@gmail.com")
                .password("Pass123!")
                .build());
        searchScreen.goToMyCarsScreen();

    }


    @Test
    public void addNewCarPositiveTest(){
        new MyCarsScreen(driver).goToAddNewCarScreen();
        CarDto car = CarDto.builder()
                .serialNumber("num-"+generatePhone(6))
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
        new AddMyCarScreen(driver).addNewCar(car);
        Assert.assertTrue(new MyCarsScreen(driver).validatePopUpMessage("Car was added!"));
    }
}
