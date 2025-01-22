package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {

    public static AppiumDriver<AndroidElement> driver;
    public Logger logger = LoggerFactory.getLogger(AppiumConfig.class);


    @BeforeMethod
    public void setup() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability( "deviceName", "Nexus5_0");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability( "appPackage", "com.telran.ilcarro");
        desiredCapabilities.setCapability( "appActivity", ".SplashActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        String urlNexus5_0 = "http://localhost:4723/wd/hub/";

        try {
            driver = new AppiumDriver<>(new URL(urlNexus5_0),desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        /*
  "platformName": "Android",
  "deviceName": "Nexus5_0",
  "platformVersion", "9.0",
  "appPackage", "com.telran.ilcarro",
  "appActivity", ".SplashActivity"

 */


    }

    @AfterMethod
    public  void tearDown(){
       driver.quit();
    }


}
