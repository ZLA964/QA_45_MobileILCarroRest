package ui_mobile;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchResultScreen;
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
    public void searchCar_lvl_PositiveTest(){
        searchScreen.typeLocation("Rehovot");
        searchScreen.typeFrom("10/03/2025");
        searchScreen.typeTo("27/03/2025");
        searchScreen.clickBtnSearchCar();
        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);
///        System.out.println(searchResultScreen.validateOpenSearchResultScreen());
///        System.out.println(searchResultScreen.notEmptyListOfCars());
        Assert.assertTrue(searchResultScreen.validateOpenSearchResultScreen()
                && searchResultScreen.notEmptyListOfCars());


    }

    @Test
    public void searchCar_lvl_calendaPositiveTest(){
        searchScreen.typeLocation("Rehovot");
 //       searchScreen.clickFrom();
        String dayFrom = "10/03/2026";
        String dayTo = "27/04/2027";
        searchScreen.setCalendarFromTO(dayFrom, dayTo);
  //      searchScreen.typeFrom("10/03/2025");
  //      searchScreen.typeTo("27/03/2025");
  //      searchScreen.clickBtnSearchCar();
        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);
///        System.out.println(searchResultScreen.validateOpenSearchResultScreen());
///        System.out.println(searchResultScreen.notEmptyListOfCars());
  //      Assert.assertTrue(searchResultScreen.validateOpenSearchResultScreen()
    //            && searchResultScreen.notEmptyListOfCars());


    }

}
