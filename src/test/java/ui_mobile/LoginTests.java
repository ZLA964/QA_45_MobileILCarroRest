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

    @Test
    public void loginNegative_lvl_Test_wrongPassword(){
        loginScreen = new LoginScreen(driver);
        loginScreen.login(UserDto.builder()
                .username("3mhulpw@gmail.com")
                .password("Pass123")
                .build());
        Assert.assertTrue(loginScreen.isErrorPanel("Login or Password incorrect"));
    }

    @Test
    public void loginNegative_lvl_Test_wrongEmail(){
        loginScreen = new LoginScreen(driver);
        loginScreen.login(UserDto.builder()
                .username("3mhulw@gmail.com")
                .password("Pass123!")
                .build());
        Assert.assertTrue(loginScreen.isErrorPanel("Login or Password incorrect"));
    }

    @Test
    public void loginNegative_lvl_Test_blankEmail(){
        loginScreen = new LoginScreen(driver);
        loginScreen.login(UserDto.builder()
                .username("")
                .password("Pass123!")
                .build());
        Assert.assertTrue(loginScreen.isErrorPanel("All fields must be filled and agree terms"));
    }

    @Test
    public void loginNegative_lvl_Test_blankPassword(){
        loginScreen = new LoginScreen(driver);
        loginScreen.login(UserDto.builder()
                .username("3mhulpw@gmail.com")
                .password(" ")
                .build());
        Assert.assertTrue(loginScreen.isErrorPanel("All fields must be filled and agree terms"));
    }
}
