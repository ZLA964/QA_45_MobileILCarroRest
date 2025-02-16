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

import java.time.LocalDate;
import java.time.Month;

import java.util.List;
import java.util.Locale;

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
    AndroidElement inputCity;
    @FindBy(id = "com.telran.ilcarro:id/editFrom")
    AndroidElement inputDateFrom;
    @FindBy(id = "com.telran.ilcarro:id/editTo")
    AndroidElement inputDateTo;
    @FindBy(id = "com.telran.ilcarro:id/searchBtn")
    AndroidElement btnYallaSearchCar;

    @FindBy(id = "android:id/button1")
    AndroidElement btnOkCalendar;

    @FindBy(id = "android:id/date_picker_header_year")
    AndroidElement btnYear;

    @FindBy(id = "android:id/date_picker_year_picker")
    AndroidElement calendarSelectYear;
    @FindBy(xpath = "//*[@resource-id='android:id/date_picker_year_picker']/*[@resource-id='android:id/text1']")
    List<AndroidElement> yearsOnCalendar;

    @FindBy(id = "android:id/next")
    AndroidElement btnMonthNext;
    @FindBy(id = "android:id/prev")
    AndroidElement btnMonthPrev;


    public void typeLocation(String taxt) {
        inputCity.sendKeys(taxt);
    }

    public void typeFrom(String text) {
        inputDateFrom.sendKeys(text);
    }

    public void typeTo(String text) {
        inputDateTo.sendKeys(text);
    }

    public void clickBtnSearchCar() {
        clickWait(btnYallaSearchCar, 3);
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

    public void clickFrom() {
        inputDateFrom.click();
    }

    public void clickTo() {
        inputDateTo.click();
    }

    public void clickCalendarOk() {
        btnOkCalendar.click();
    }

    public void setCalendarFromTO(String dayFrom, String dayTo) {
        clickFrom();
        setCalendar(dayFrom);
        //       inputTo.click();
        // setCalendar(dayTo);
    }

    private void setCalendar(String date) {

        String[] dayArray = date.split("/");
        String yearOfDay = dayArray[2];
        String month = dayArray[1];
        String day = dayArray[0];
        clickWait(btnYear, 3);
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/date_picker_year_picker")));
        System.out.println("Years found: " + yearsOnCalendar.size());
        AndroidElement yearForClick = yearsOnCalendar.stream()
                .filter(y -> yearOfDay.equalsIgnoreCase(y.getText()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Year not found: " + yearOfDay));
        yearForClick.click();
        System.out.println("Year is selected!");
        for (AndroidElement year : yearsOnCalendar) {
            System.out.println("Year: " + year.getText());
        }
    }


    public void findCarWithCalendar(String city, String dateStart, String dateEnd) {
  //      clickWait(inputCity, 5);  // 12 March 2026
//        new WebDriverWait(driver,5)
//                .until(ExpectedConditions.visibilityOfElementLocated
//                                (By.id("com.telran.ilcarro:id/editFrom")));
        inputCity.sendKeys(city);
        inputDateFrom.click();
        setDateCalendar(dateStart);
        inputDateTo.click();
        setDateCalendar(dateEnd);
    }

    private void setDateCalendar(String date) {

        String[] arrayDate = date.split(" ");
        // ===== year =====
        if (LocalDate.now().getYear() != Integer.parseInt(arrayDate[2])) {
            clickWait(btnYear, 5);
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@text='" + arrayDate[2] + "']")
                    )).click();
        }
        //  =====  month =====
        int month = returnNumOfMonth(arrayDate[1]);
        int currentMonth = LocalDate.now().getMonthValue() + 1; // correct for Bag!!!
        int quantityClick = (month - currentMonth);  // by modul
        if(quantityClick >  0){
            for (int i =0 ; i < quantityClick ; i++){
                clickWait(btnMonthNext,2);
            }
        } else if( quantityClick < 0 ) {
                for (int i =0 ; i < Math.abs(quantityClick) ; i++){
                    clickWait(btnMonthPrev,2);
                }
        }

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@content-desc='" + date + "']"))).click();
        btnOkCalendar.click();
        try {
            new WebDriverWait(driver, 1)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
        } catch (TimeoutException e) {
            System.out.println("calendar closed");
        }
    }

    private int returnNumOfMonth(String month){
        Month month1 = Month.valueOf(month.toUpperCase(Locale.ENGLISH));
        return month1.getValue();
    }
}

