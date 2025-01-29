package ui_mobile;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class SearchCarTests extends AppiumConfig {

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
    }

    @Test
    public void searchCar_lvl_Test(){
        searchScreen.typeLocation("Haifa");
        searchScreen.typeFrom("10/03/2025");
        searchScreen.typeTo("27/03/2025");
        searchScreen.clickBtnSearchCar();

    }

}
