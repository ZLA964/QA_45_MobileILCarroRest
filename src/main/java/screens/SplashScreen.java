package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.FindBy;

public class SplashScreen extends BaseScreen {

    public SplashScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/versionText")
    AndroidElement versionApp;

    @FindBy(id = "com.telran.ilcarro:id/imageView3")
    AndroidElement logoApp;


    public boolean validateVersion(String version) {
        return textInElementPresent(versionApp, version, 5);
    }

    public long displayTime() {
        long start;
        long stop;
        do {
            start = System.currentTimeMillis();
        } while (!logoApp.isDisplayed());
        stop = System.currentTimeMillis();
        try {
            while (logoApp.isDisplayed()) {
                stop = System.currentTimeMillis();
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("screen was changed");
        }
        return stop - start;

    }

}
