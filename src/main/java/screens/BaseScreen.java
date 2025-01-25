package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseScreen {

    AppiumDriver<AndroidElement> driver ;

    @FindBy(xpath = "/hierarchy/android.widget.Toast")
    AndroidElement popUpMessageSuccess;

    @FindBy(id = "android:id/parentPanel")
    AndroidElement popUpPanel;

    @FindBy(id = "android:id/alertTitle")
    AndroidElement popUpTitle;

    @FindBy(id = "android:id/message")
    AndroidElement popUpPanelMessage;

    @FindBy(id = "android:id/button1")
    AndroidElement btnPopUpPanel;


    public BaseScreen(AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean textInElementPresent(AndroidElement element, String text, int time){
        try {
            return new WebDriverWait(driver, time)
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (TimeoutException e) {
       //     e.printStackTrace();
            System.out.println("textInElementPresent --> created exception");
            return false;
        }
    }
/*/
    public void pause(int time) {
        try {
            Thread.sleep(1000L*time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
*/
    public void clickWait(AndroidElement element, int time){
        new WebDriverWait(driver,time)
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public boolean validatePopUpMessage(String message){
        return textInElementPresent(popUpMessageSuccess, message, 5);
    }


}
