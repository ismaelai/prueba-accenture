package selenium;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class UserLogin {


    // Navegador
    WebDriver driver;

    String URL = "https://www.saucedemo.com/";

    @BeforeEach
    void setUp() {
        String projectPath = System.getProperty("user.dir"); // ruta del proyecto
        System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver(); // Google Chrome
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void loginSucess() throws Exception {

        driver.get(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
           .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-button")));
        driver.manage().window().maximize();


        //Enter user name
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");

        // Enter password
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");

        // click login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        Thread.sleep(3000);

        Assertions.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }

        @Test
        void loginFailed() throws Exception {

            driver.get(URL);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-button")));
            driver.manage().window().maximize();


            //Enter user name
            driver.findElement(By.cssSelector("#user-name")).sendKeys("pepe");

            // Enter password
            driver.findElement(By.cssSelector("#password")).sendKeys("12345");

            // click login button
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            Thread.sleep(3000);

            Assertions.assertNotEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }

}


