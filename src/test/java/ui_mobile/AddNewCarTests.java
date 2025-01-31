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
    MyCarsScreen myCarsScreen;

    @BeforeMethod
    public void login() {
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
    public void addNewCarPositiveTest() {
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
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
        new AddMyCarScreen(driver).addNewCar(car);
        Assert.assertTrue(myCarsScreen.validatePopUpMessage("Car was added!"));
    }

    @Test
    public void addNewCarPositiveTest2() {


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
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddMyCarScreen(driver).addNewCar(car);
        myCarsScreen.scrollToLastElementAvto();
        Assert.assertEquals(myCarsScreen.scrollToLastElementAvto(),car.getSerialNumber());
//        Assert.assertTrue(myCarsScreen.validatePopUpMessage("Car was added!"));
    }
}
