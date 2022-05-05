package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CartSteps {

    WebDriver driver;

    String URL = "https://www.saucedemo.com/";

    @Given("User is at directory products page")
    public void user_is_at_directory_products_page() {
        String projectPath = System.getProperty("user.dir"); // ruta del proyecto
        System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver(); // Google Chrome

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

        Assertions.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }

    @And("There are products in the shop available for purchase")
    public void there_are_products_in_the_shop_available_for_purchase() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        driver.manage().window().maximize();

        //Check if there are items in the store (product title)
        WebElement product = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[2]/div[1]"));
        assertNotNull(product);
    }

    @When("User adds products in the shopping cart")
    public void user_adds_products_in_the_shopping_cart() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        driver.manage().window().maximize();

        //Check if add to cart button exits and click
        WebElement addButton = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        addButton.click();

    }

    @And("User clicks on the cart button")
    public void user_clicks_on_the_cart_button() {

        //go to cart page
        WebElement cartButton = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cartButton.click();
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");

    }

    @Then("User proceeds to complete the purchase with checkout button")
    public void user_proceeds_to_complete_the_purchase_with_checkout_button() {

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
