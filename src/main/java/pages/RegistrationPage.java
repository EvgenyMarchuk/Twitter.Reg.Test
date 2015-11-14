package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverSingleton.getDriver;
import static helpers.Locators.get;

public class RegistrationPage {
    public static final By FULL_NAME_FIELD = get("signup.fullNameField");
    public static final By LOGIN_FIELD = get("signup.emailField");
    public static final By PASSWORD_FIELD = get("signup.passwordField");
    public static final By SUBMIT_BUTTON = get("signup.submitButton");
    public static final By ACTIVE_MESSAGE = get("signup.activeMessage");
    public static final By ACTIVE_EMAIL_MESSAGE = get("signup.activeEmailMessage");
    public static final By ACTIVE_PASSWORD_MESSAGE = get("signup.activePasswordMessage");
    public static final By ACTIVE_USER_MESSAGE = get("signup.activeUserMessage");

    public static void registration(String user, String email, String pass) {
        fillForm(user, email, pass);
        getDriver().findElement(SUBMIT_BUTTON).click();
    }

    public static void fillForm(String user, String email, String pass){
        getDriver().findElement(FULL_NAME_FIELD).sendKeys(user);
        getDriver().findElement(LOGIN_FIELD).sendKeys(email);
        getDriver().findElement(PASSWORD_FIELD).sendKeys(pass);
    }

    public static List<WebElement> getValidationMessage(){
        return getDriver().findElements(ACTIVE_MESSAGE);
    }
}
