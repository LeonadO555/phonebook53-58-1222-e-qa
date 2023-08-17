package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    public WebDriver driver;

    protected void init() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
    }

    protected void stop() {
        driver.quit();
    }

}
