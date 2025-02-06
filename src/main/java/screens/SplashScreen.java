package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void goToSearchScreen(){
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("com.telran.ilcarro:id/findTitle")));
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("element findTitle not find");
        }

    }

}
