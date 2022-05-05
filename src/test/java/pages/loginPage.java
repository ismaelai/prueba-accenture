package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {

    WebDriver driver;

    private By username_credentials = By.id("user-name");
    private By password_credentials = By.id("password");
    private By btnLogin = By.id("login-button");


    public loginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(username_credentials).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(password_credentials).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(btnLogin).click();
    }

}
