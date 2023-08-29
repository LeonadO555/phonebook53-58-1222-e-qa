//package e2e.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//public class EmailPage extends ContactInfoPage {
//
//    public EmailPage(WebDriver driver) {
//        super(driver);
//    }
//
//    @FindBy(xpath = "//*[@id='contacts-list']")
//    WebElement saveButton;
//
//    @FindBy(xpath = "//*[@id='contacts-list']")
//    WebElement editButton;
//
//    @FindBy(xpath = "//*[@id='contacts-list']")
//    WebElement deleteButton;
//
//
//    public void waitForLoading() {
//        getWait().forVisibility(emailDialog);
//        getWait().forClickable(saveButton);
//        getWait().forClickable(saveButton);
//
//    }
//
//    public void openEditDropdown(String email) {
//        driver.findElement(By.xpath("//*[contains]"))
//    }
//
//    public void openEditDialog() {
//        editButton.click();
//    }
//
//    public void
//}
