package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static WebDriver driver;
    private DriverSingleton(){}

    public static WebDriver getDriver(){
        if (driver == null){
            initDriver("default");
        }
        return driver;
    }

    public static void quit(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

    private static void initDriver(String browser) {
        String browserName = System.getProperty("browser", browser);
        String remote = System.getProperty("remote", "local");
        if (remote.equals("local")){
            switch (browserName) {
                case "firefox": default:
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    break;
            }
        }else {
            DesiredCapabilities caps;
            switch (browserName) {
                case "chrome": default:
                    caps = DesiredCapabilities.chrome();
                    break;
                case "firefox":
                    caps = DesiredCapabilities.firefox();
                    break;
                case "ie":
                    caps = DesiredCapabilities.internetExplorer();
                    break;
            }
            try {
                driver = new RemoteWebDriver(new URL(remote), caps);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
