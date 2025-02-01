package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultScreen extends BaseScreen {
    public SearchResultScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/resultTitle")
    AndroidElement resultTitle;

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/resultRv']//*[contains(@resource-id, 'com.telran.ilcarro:id/rowContainer')]")
    List<AndroidElement> listCars;

    public boolean validateOpenSearchResultScreen() {
        return textInElementPresent(resultTitle, "Search result", 7);
    }

    public boolean notEmptyListOfCars() {
        int numberCars = listCars.size();
        System.out.println("numberCars on screenResult-> " + numberCars);
        return !listCars.isEmpty();
    }

}
