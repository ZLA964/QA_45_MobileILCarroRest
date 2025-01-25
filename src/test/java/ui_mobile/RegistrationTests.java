package ui_mobile;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.RandomUtils_lvl;
import utils.TestNGListener;

import static helper.RandomUtils.*;

@Listeners(TestNGListener.class)
public class RegistrationTests extends AppiumConfig {
    SearchScreen searchScreen;

    @BeforeMethod
    public void beforeTest(){
        new SplashScreen(driver);
        searchScreen = new SearchScreen(driver);
        searchScreen.goToRegistrationScreen();
    }


    @Test
    public void registrationPositiveTest_lvl(){
        UserDto user = UserDto.builder()
                .firstName(RandomUtils_lvl.generateStringTextOnly(4))
//                .firstName("Buba")
                .lastName(RandomUtils_lvl.generateStringTextOnly_stream(9))
//                .lastName("Kostrov")
                .username(RandomUtils_lvl.generateEmail(7))
                .password("Pass123!")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeUserData(user);
        logger.info("\n\t=== user for registration === \n\t" + user + "\n\t=====  \t\t\t=====");
        registrationScreen.clickBtnYalla();
        boolean regSuccess = searchScreen.checkPopUpRegSuccess();
        logger.info(" registration success is --> " + regSuccess);
        Assert.assertTrue(regSuccess);
    }

    @Test
    public void registrationPositiveTest() {
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateEmail(10))
                .password("Qwerty123!")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForn(user);
        Assert.assertTrue(registrationScreen.validateMessageSuccess("Registration success!"));
    }

    @Test
    public void registrationNegative_lvl_Test_wrongEmail() {
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateString(10))
                .password("Qwerty123!")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForn(user);
        Assert.assertTrue(registrationScreen.validateMessageSuccess("Registration success!"));
    }

}
