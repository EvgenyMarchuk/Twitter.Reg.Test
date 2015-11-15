import helpers.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegistrationPage;

import static helpers.DriverSingleton.getDriver;
import static helpers.Helpers.saveScreenshot;

public class TwitterTest extends TestBase{

    private final String FULL_NAME = "";
    private final String VALID_EMAIL = "mails@trp.ru";
    private final String INVALID_EMAIL = "q123";
    private final String VALID_PASSWORD = "q1Q2W#Er";
    private final String INVALID_PASSWORD = "11";
    private final String EMAIL_ERROR = "Введите действительный адрес электронной почты";
    private final String PASSWORD_ERROR = "Ваш пароль должен состоять как минимум из 6 символов.";

    @Test
    public void invalidEmailTest() {
        RegistrationPage.registration(FULL_NAME, INVALID_EMAIL, VALID_PASSWORD);
        saveScreenshot("test.jpeg");
        Assert.assertEquals(RegistrationPage.getValidationMessage().size(), 1);
        Assert.assertTrue(getDriver().findElement(RegistrationPage.ACTIVE_EMAIL_MESSAGE).isDisplayed());
        Assert.assertEquals(getDriver().findElement(RegistrationPage.ACTIVE_EMAIL_MESSAGE).getText(),
                EMAIL_ERROR);
    }

    @Test
    public void invalidPasswordTest() {
        RegistrationPage.registration(FULL_NAME, VALID_EMAIL, INVALID_PASSWORD);
        Assert.assertEquals(RegistrationPage.getValidationMessage().size(), 1);
        Assert.assertTrue(getDriver().findElement(RegistrationPage.ACTIVE_PASSWORD_MESSAGE).isDisplayed());
        Assert.assertEquals(getDriver().findElement(RegistrationPage.ACTIVE_PASSWORD_MESSAGE).getText(),
                PASSWORD_ERROR);
    }
}

