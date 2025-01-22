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
import utils.RandomUtils;
import utils.TestNGListener;

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
    public void registrationPositiveTest(){
        UserDto user = UserDto.builder()
                .firstName(RandomUtils.generateStringTextOnly(4))
//                .firstName("Buba")
                .lastName(RandomUtils.generateStringTextOnly_stream(9))
//                .lastName("Kostrov")
                .username(RandomUtils.generateEmail(7))
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
}
