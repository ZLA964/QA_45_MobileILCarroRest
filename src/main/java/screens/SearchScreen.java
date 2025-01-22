package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchScreen extends BaseScreen{

    public SearchScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement btnMoreOption;

    @FindBy(xpath = "//*[@text='Registration']")
    AndroidElement btnRegistration;

    @FindBy(xpath = "//*[@text='Login']")
    AndroidElement btnLogin;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup\n")
    AndroidElement popUpRegSuccess;

    public void goToRegistrationScreen(){
//        btnMoreOption.click();
        clickWait(btnMoreOption,5);
        btnRegistration.click();
    }

    public boolean checkPopUpRegSuccess() {
        try {
            WebElement regSuccess = new WebDriverWait(driver, 7)
                    .until(ExpectedConditions.visibilityOf(popUpRegSuccess));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Not success registration");
            return false;
        }
    }
}
