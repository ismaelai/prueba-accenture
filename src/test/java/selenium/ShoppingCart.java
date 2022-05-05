package selenium;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
import pages.loginPage;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShoppingCart {

   WebDriver driver;
   loginPage login;
   String URL = "https://www.saucedemo.com/";

    @BeforeEach
    void setUp() throws InterruptedException {
        String projectPath = System.getProperty("user.dir"); // ruta del proyecto
        System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver(); // Google Chrome

        driver.get(URL);
        //Enter user name
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");

        // Enter password
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");

        Thread.sleep(1000);
        // click login button
        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
        loginButton.click();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void checkProducts() throws Exception {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        driver.manage().window().maximize();

        Thread.sleep(1000);

        //Check if there are items in the store (product title)
        WebElement product = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[2]/div[1]"));
        assertNotNull(product);

    }

    @Test
    void addToCart() throws Exception {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        driver.manage().window().maximize();

        //Check if add to cart button exits and click
        WebElement addButton = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        addButton.click();
    }

    @Test
    void clickCartButton() throws Exception {
        driver.manage().window().maximize();

        //add items to cart
        WebElement addButton = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        addButton.click();

        //go to cart page
        WebElement cartButton = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cartButton.click();
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }
    @Test
    void proceedToCheckout() throws Exception {
        driver.manage().window().maximize();

        //add items to cart
        WebElement addButton = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        addButton.click();

        //go to cart page
        WebElement cartButton = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cartButton.click();

        // complete checkout
        WebElement checkoutButton = driver.findElement(By.xpath("//*[@id=\"checkout\"]"));
        checkoutButton.click();

        // fill out shipping details
        driver.findElement(By.id("first-name")).sendKeys("Ismael");
        driver.findElement(By.id("last-name")).sendKeys("Gonzalez");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();


        // check out summary and click finish
        WebElement overview = driver.findElement(By.className("title"));
        assertEquals(overview.getText(),"CHECKOUT: OVERVIEW" );
        driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();

        // verify order is completed
        WebElement completed = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));
        assertEquals(completed.getText(), "THANK YOU FOR YOUR ORDER");

    }
}
