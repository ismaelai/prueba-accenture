package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.loginPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginSteps {

    WebDriver driver;
    loginPage login;


    @Given("user is on the website")
    public void user_is_on_login_page() {
        String projectPath = System.getProperty("user.dir"); // ruta del proyecto
        System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver(); // Google Chrome

        String URL = "https://www.saucedemo.com/";
        driver.get(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-button")));
        driver.manage().window().maximize();
    }

    @When("^user enters (.*) and (.*)$")
    public void user_enters_username_and_password(String username, String password) throws InterruptedException {

        login = new loginPage(driver);
        login.enterUsername(username);
        login.enterPassword(password);
        Thread.sleep(3000);
    }


    @When("clicks on login button")
    public void clicks_on_login_button() {

        login.clickLogin();
    }

    @Then("logged as user in home items directory")
    public void loggedAsUserInHomeItemsDirectory() {
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }


}
