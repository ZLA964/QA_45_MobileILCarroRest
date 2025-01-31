package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class MyCarsScreen extends BaseScreen {
    int heigt = driver.manage().window().getSize().getHeight();
    int width = driver.manage().window().getSize().getWidth();

    public MyCarsScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddNewCar;
/*/
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/myCarsRv]//android.widget.TextView[last()]")
    AndroidElement lastElement;
*/
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/myCarsRv']//*[@resource-id='com.telran.ilcarro:id/myCarSerialTxt']")
    List<AndroidElement> carList;

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/LinearLayout']")
    AndroidElement firstCarList;
    @FindBy(id = "android:id/button1")
    AndroidElement btnYes;

    public void goToAddNewCarScreen() {
        clickWait(btnAddNewCar, 5);
    }

    public String scrollToLastElementAvto() {
        heigt = driver.manage().window().getSize().getHeight();
        width = driver.manage().window().getSize().getWidth();

        TouchAction<?> touchAction = new TouchAction<>(driver);
        String second="";
        boolean flag = true;
        while (flag) {
            String first = carList.get(carList.size() - 1).getText();
            System.out.println("first --> " + first);
            touchAction.longPress(PointOption.point(5, 5 * heigt / 6))
                    .moveTo(PointOption.point(5, heigt / 6))
                    .release().perform();
            pause(1);
            second = carList.get(carList.size() - 1).getText();
            System.out.println("second --> " + second);
            flag = !first.equals(second);
        }
        return second;
    }

    public void deleteFirstCar() {
        int yElement = firstCarList.getLocation().getY();

        int heightElement = firstCarList.getSize().getHeight();
        int yMidlElement = yElement +  heightElement/2;
                System.out.println( firstCarList.getLocation().getY());
        System.out.println( firstCarList.getSize().getHeight());

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(width/10*9, yMidlElement ))
                .moveTo(PointOption.point(width/10, yMidlElement))
                .release().perform();

        clickWait(btnYes,2);
    }
}
