package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.Wait;

public class PageBase {
    public WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public Wait getWait() {
        return new Wait(driver);
    }

    public void click(WebElement element) {
        element.isDisplayed();
        element.click();
    }

    public void selectDropdownText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }


    public void login() {

    }
}
