package screens;

import dto.UserDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class LoginScreen extends BaseScreen {

    public LoginScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editLoginEmail")
    AndroidElement inputEmail;

    @FindBy(id = "com.telran.ilcarro:id/editLoginPassword")
    AndroidElement inputPassword;

    @FindBy(id = "com.telran.ilcarro:id/loginBtn")
    AndroidElement btnYalla;


    public void login(UserDto user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        btnYalla.click();
    }

    public boolean isLoginSuccess() {
        return validatePopUpMessage("Login success!");
    }

    public boolean isErrorPanel(String text) {
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOf(popUpPanel));
            if (textInElementPresent(popUpTitle, "Error", 1) &&
                    textInElementPresent(popUpPanelMessage, text, 1)) {
                btnPopUpPanel.click();
            } else {
                System.out.println("popUpTitle -> " + popUpTitle.getText());
                System.out.println("popUpPanelMessage -> " + popUpPanelMessage.getText());
                return false;
            }
            return true;
        } catch (TimeoutException | StaleElementReferenceException e) {
            System.out.println("No PopUP of Error on Login!");
            System.out.println(e.getMessage());
//            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            return false;
        }
    }


}
