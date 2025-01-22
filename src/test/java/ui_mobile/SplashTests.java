package ui_mobile;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import screens.SplashScreen;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class SplashTests extends AppiumConfig {

    @Test
    public void validateVersionApp() {
        Assert.assertTrue(new SplashScreen(driver)
                .validateVersion("Version 1.0.0"));
    }

    @Test
    public void validateTimeSplah() {
        long splashTime = new SplashScreen(driver).displayTime();
        logger.info("Splash screen time -> " + splashTime +" mSec");
        Assert.assertTrue(splashTime <= 5000L);
    }
}
