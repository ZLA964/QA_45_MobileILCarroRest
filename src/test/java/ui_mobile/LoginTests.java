package ui_mobile;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class LoginTests extends AppiumConfig {

    LoginScreen loginScreen;

    @BeforeMethod
    public void beforeLogin(){
        new SplashScreen(driver);
        new SearchScreen(driver).goToLoginScreen();
    }

    @Test
    public void loginPositiveTest(){
        loginScreen = new LoginScreen(driver);
        loginScreen.login(UserDto.builder()
                        .username("3mhulpw@gmail.com")
                        .password("Pass123!")
                .build());
        Assert.assertTrue(loginScreen.isLoginSuccess());
    }


}
