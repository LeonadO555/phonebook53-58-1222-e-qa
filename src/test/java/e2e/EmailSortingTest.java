//package e2e;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.time.Duration;
//import java.util.List;
//
//public class EmailSortingTest {
//
//    private WebDriver driver;
//
//    @BeforeClass
//    public void setUp() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        driver = new ChromeDriver(options);
//    }
//
//    @Test
//    public void testEmailSorting() {
//        driver.get("http://phonebook.telran-edu.de:8080/contacts");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
//        driver.findElement(By.name("username")).sendKeys("test@gmail.com");
//        driver.findElement(By.name("password")).sendKeys("test@gmail.com");
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//
//        driver.findElement(By.xpath("//th[text()='Email']")).click();
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[text()='Email' and @aria-sort='ascending']")));
//
//        List<WebElement> emailElements = driver.findElements(By.xpath("//td[contains(@data-label,'Email')]"));
//
//        for (int i = 1; i < emailElements.size(); i++) {
//            String email1 = emailElements.get(i - 1).getText();
//            String email2 = emailElements.get(i).getText();
//            Assert.assertTrue(email1.compareTo(email2) <= 0, "Emails are not in ascending order");
//        }
//    }
//
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}