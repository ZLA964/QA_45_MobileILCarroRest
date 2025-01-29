package ui_mobile;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.RandomUtils_lvl;
import utils.TestNGListener;

import static helper.RandomUtils.*;

@Listeners(TestNGListener.class)
public class RegistrationTests extends AppiumConfig {
    SearchScreen searchScreen;

    String userName = "bm8hreura5@mail.com";

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
        registrationScreen.setCheckBox();
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
        Assert.assertTrue(new SearchScreen(driver).validateMessageSuccess("Registration success!"));
    }

    @Test
    public void registrationNegative_lvl_Test_invalidPassword() {
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateEmail(10))
                .password("Qwerty")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForn(user);
        Assert.assertTrue(registrationScreen
                .isErrorPanel("{password= At least 8 characters; Must contain at least 1 " +
                        "uppercase letter, 1 lowercase letter, and 1 number; Can contain special " +
                        "characters [@$#^&*!]}"));
    }

    @Test
    public void registrationNegative_lvl_Test_blankLastName() {
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(" ")
                .username(generateEmail(10))
                .password("Qwerty123!")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForn(user);
        Assert.assertTrue(registrationScreen
                .isErrorPanel("All fields must be filled and agree terms"));
    }

    @Test
    public void registrationNegative_lvl_Test_blankName() {
        UserDto user = UserDto.builder()
                .firstName(" ")
                .lastName(generateString(10))
                .username(generateEmail(10))
                .password("Qwerty123!")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForn(user);
        Assert.assertTrue(registrationScreen
                .isErrorPanel("All fields must be filled and agree terms"));
    }

    @Test
    public void registrationNegative_lvl_Test_noBox() {
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateEmail(10))
                .password("Qwerty123!")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeUserData(user);
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(registrationScreen
                .isErrorPanel("All fields must be filled and agree terms"));
    }

    @Test
    public void registrationNegative_lvl_Test_invalidEmail() {
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateString(10))
                .password("Qwerty123!")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForn(user);
        Assert.assertTrue(registrationScreen
                .isErrorPanel("{username=must be a well-formed email address}"));
    }

    @Test
    public void registrationNegativeTest_duplicateUser() {
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(userName)
                .password("Qwerty123!")
                .build();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForn(user);
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("User already exists"));
    }
}
