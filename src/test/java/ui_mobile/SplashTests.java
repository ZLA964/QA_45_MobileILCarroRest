package ui_mobile;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;

public class SplashTests extends AppiumConfig {

    @Test
    public void validateVersionApp() {
        Assert.assertTrue(new SplashScreen(driver)
                .validateVersion("Version 1.0.0"));

    }
}
