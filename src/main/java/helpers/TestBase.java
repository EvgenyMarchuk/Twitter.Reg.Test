package helpers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static helpers.DriverSingleton.getDriver;

public class TestBase {
    private final static String BASE_URL = "https://twitter.com/signup";

    @BeforeMethod
    public void setUp(){
        getDriver().navigate().to(BASE_URL);
    }

    @AfterMethod
    public void tearDown(){
        DriverSingleton.quit();
    }
}

