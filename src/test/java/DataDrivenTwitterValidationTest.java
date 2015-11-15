import helpers.DataProviders;
import helpers.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegistrationPage;

import static helpers.DriverSingleton.getDriver;

public class DataDrivenTwitterValidationTest extends TestBase{

    @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
    public void registrationDataTest(String user, String email, String pass,
                                     String userValidation, String emailValidationText, String passValidationTest){
        RegistrationPage.registration(user, email, pass);
        int validation = 0;
        if(userValidation.length() > 2){
            validation++;
            WebElement user_validation = getDriver().findElement(RegistrationPage.ACTIVE_USER_MESSAGE);
            Assert.assertTrue(user_validation.isDisplayed());
            Assert.assertEquals(userValidation, user_validation.getText());
        }
        if(passValidationTest.length() > 2){
            validation++;
            WebElement pass_validation = getDriver().findElement(RegistrationPage.ACTIVE_PASSWORD_MESSAGE);
            Assert.assertTrue(pass_validation.isDisplayed());
            Assert.assertEquals(passValidationTest, pass_validation.getText());
        }
        if(emailValidationText.length() > 2){
            validation++;
            WebElement email_validation = getDriver().findElement(RegistrationPage.ACTIVE_EMAIL_MESSAGE);
            Assert.assertTrue(email_validation.isDisplayed());
            Assert.assertEquals(emailValidationText, email_validation.getText());
        }
        Assert.assertEquals(RegistrationPage.getValidationMessage().size(), validation);
    }
}
