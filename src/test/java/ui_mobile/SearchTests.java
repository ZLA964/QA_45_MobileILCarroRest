package ui_mobile;

import config.AppiumConfig;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;

public class SearchTests extends AppiumConfig {

    SearchScreen searchScreen;

    @BeforeMethod
    public void gotoSearchScreen() {
        new SplashScreen(driver).goToSearchScreen();
        searchScreen = new SearchScreen(driver);
    }


    @Test
    public void searchPositiveTest_withCalendar(){
            searchScreen.findCarWithCalendar("Rehovot", "10 February 2026", "12 June 2026");
    }
}
