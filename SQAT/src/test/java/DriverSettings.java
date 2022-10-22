import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSettings {
    ConfigProperties config = ConfigFactory.create(ConfigProperties.class);
    public void initDriver(){
        switch (config.browser()){
            case "firefox":
                WebDriver firefox = new FirefoxDriver();
                break;
            case "chrome":
                WebDriver chrome = new ChromeDriver();
                break;
            case "edge":
                WebDriver edge = new EdgeDriver();
                break;
        }
    }
}
