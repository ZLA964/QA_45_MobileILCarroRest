package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchScreen extends BaseScreen {

    public SearchScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement btnMoreOption;
    @FindBy(xpath = "//*[@text='Registration']")
    AndroidElement btnRegistration;
    @FindBy(xpath = "//*[@text='Login']")
    AndroidElement btnLogin;
    @FindBy(xpath = "/hierarchy/android.widget.Toast")
    AndroidElement popUpRegSuccess;
    @FindBy(xpath = "//*[@text='My Cars']")
    AndroidElement btnMyCars;

    @FindBy(id = "com.telran.ilcarro:id/editLocation")
    AndroidElement inputLocation;
    @FindBy(id = "com.telran.ilcarro:id/editFrom")
    AndroidElement inputFrom;
    @FindBy(id = "com.telran.ilcarro:id/editTo")
    AndroidElement inputTo;
    @FindBy(id = "com.telran.ilcarro:id/searchBtn")
    AndroidElement btnYallaSearchCar;

    public void typeLocation(String taxt){
        inputLocation.sendKeys(taxt);
    }

    public void typeFrom(String text){
        inputFrom.sendKeys(text);
    }

    public void typeTo(String text){
        inputTo.sendKeys(text);
    }

    public void clickBtnSearchCar(){
        clickWait(btnYallaSearchCar,3);
    }

    public void goToRegistrationScreen() {
//        btnMoreOption.click();
        clickWait(btnMoreOption, 5);
        btnRegistration.click();
    }

    public boolean checkPopUpRegSuccess() {
        try {
//            new WebDriverWait(driver, 7)
//                    .until(ExpectedConditions.visibilityOf(popUpRegSuccess));
//            return true;
            new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
            return textInElementPresent(popUpRegSuccess, "Registration success!", 5);
        } catch (StaleElementReferenceException | TimeoutException e) {
            //          e.printStackTrace();
            System.out.println("Not success registration");
            return false;
        }
    }

    public void goToLoginScreen() {
        clickWait(btnMoreOption, 5);
        clickWait(btnLogin, 5);
    }

    public boolean validateMessageSuccess(String message) {
        return textInElementPresent(popUpMessageSuccess, message, 5);
    }

    public void goToMyCarsScreen() {
        clickWait(btnMoreOption, 5);
        clickWait(btnMyCars, 5);
    }
}
