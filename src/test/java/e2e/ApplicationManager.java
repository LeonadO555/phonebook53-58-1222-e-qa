package e2e;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ApplicationManager {
    public WebDriver driver;

    protected void init() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Irina Ovsianko\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.get("http://phonebook.telran-edu.de:8080/");
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
    }

    protected void stop() {
        driver.quit();
    }

}
