package ui_mobile;

import config.AppiumConfig;
import config.CarControler;
import dto.CarDto;
import dto.CarsDto;
import dto.UserDto;
import io.restassured.response.Response;
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
    public void addNewCarPositive_assur_Test() {
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
        CarControler carControler = new CarControler();
        carControler.login();
        Response responseBefore = carControler.getUserCar(carControler.tokenDto.getAccessToken());
        CarDto[] arrayCar = responseBefore.body().as(CarsDto.class).getCars();
        int index=0;
        for( CarDto carApi : arrayCar){

            if(carApi.equals(car)) {
                System.out.println("car->" + carApi);
            }
            index++;
 //           System.out.println("car->" + carDto);
        }
        Assert.assertEquals(car, arrayCar[index-1]);

//        Assert.assertTrue(myCarsScreen.validatePopUpMessage("Car was added!"));
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
        Assert.assertEquals(myCarsScreen.scrollToLastElementAvto(), car.getSerialNumber());
    }

    @Test
    public void addNewCarPositivetive_lvl_Test_noAbout() {
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel("Gas")
                .year("1999")
                .seats(4)
                .about("")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddMyCarScreen(driver).addNewCar(car);
        myCarsScreen.scrollToLastElementAvto();
        Assert.assertEquals(myCarsScreen.scrollToLastElementAvto(), car.getSerialNumber());
    }

    @Test
    public void addNewCarNegative_lvl_Test_noSeats() {  // BUG!!  seat = 0 adding Car!
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel("Gas")
                .year("1975")
                .seats(0)
                .about("best of the best")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddMyCarScreen(driver).addNewCar(car);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
   }

    @Test
    public void addNewCarNegative_lvl_Test_noCity() {
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("")
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
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }
/*
    @Test
    public void addNewCarNegative_lvl_Test_PriceZero() {// BUG !!  and if Price < 0 !!!
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(0.0)
                .carClass("Hi")
                .fuel("Gas")
                .year("1975")
                .seats(0)
                .about("best of the best")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddMyCarScreen(driver).addNewCar(car);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }
*/

    @Test
    public void addNewCarNegative_lvl_Test_noPrice() {// BUG !!
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(-1.0)
                .carClass("Hi")
                .fuel("Gas")
                .year("1975")
                .seats(0)
                .about("best of the best")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddMyCarScreen(driver).addNewCarNoPrice(car);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }


    @Test
    public void addNewCarNegative_lvl_Test_noClass() {
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("")
                .fuel("Gas")
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddMyCarScreen(driver).addNewCar(car);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("{carClass=must not be blank}"));
    }

    @Test
    public void addNewCarNegative_lvl_Test_noYear() {
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel("Gas")
                .year("")
                .seats(4)
                .about("best of the best")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddMyCarScreen(driver).addNewCar(car);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("{year=must not be blank}"));
    }

    @Test
    public void addNewCarNegative_lvl_Test_noModel() {
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("ZAZ")
                .model("")
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
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }

    @Test
    public void addNewCarNegative_lvl_Test_noFuel() {  // BUG!!  adding pass!!!
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
        new AddMyCarScreen(driver).addNewCarNoFuel(car);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }

    @Test
    public void addNewCarNegative_lvl_Test_noManufacture() {
        CarDto car = CarDto.builder()
                .serialNumber("num-" + generatePhone(6))
                .manufacture("")
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
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }


    @Test
    public void addNewCarNegative_lvl_Test_noNumber() {
        CarDto car = CarDto.builder()
                .serialNumber("")
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
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }
}
