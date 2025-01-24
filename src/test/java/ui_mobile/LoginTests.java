package ui_mobile;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

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
    }
}
