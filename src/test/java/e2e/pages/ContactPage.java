package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage extends PageBase {

    Header header = new Header(driver);

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='contacts-list']")
    WebElement contactList;

    public void waitForLoading() {
        getWait().forVisibility(contactList);
    }

    public boolean isContactPresent(String contactId) {
        try {
            makeRowLocatorById(contactId).isDisplayed();
            return true;
        } catch (Exception e) {
            System.out.println("Contact with id " + contactId + " is not present");
            return false;
        }
    }


    public boolean booleanContactPresent(String contactId) {
        try {
            makeRowLocatorById(contactId).isDisplayed();
            return true;
        } catch (Exception e) {
            System.out.println("Contact with id" + contactId + "is not present");
            return false;
        }

    }

    public WebElement makeRowLocatorById(String contactId) {
        return driver.findElement(By.xpath("//*[@ng-reflect-router-link='/contacts/" + contactId + "']"));

    }

    public void openContactById(String contactId) {
        makeRowLocatorById(contactId).click();
    }

    // public void okay() throws IOException {
    //  takeAndCompareScreenShot("contactList", contactList);
    // }
}
