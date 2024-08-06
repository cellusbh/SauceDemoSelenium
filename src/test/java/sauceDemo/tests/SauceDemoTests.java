package sauceDemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sauceDemo.pages.*;

import java.time.Duration;

@DisplayName("automated tests on saucedemo.com")
public class SauceDemoTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        // open the driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @AfterEach
    public void tearDown() {

        // close the driver
        if (driver != null) {
            driver.quit();
        }

    }

    @Test
    @DisplayName("successful sign up")
    public void testSignUpSuccess() {

        String swagLabs = new LoginPage(driver)
                .accessPage()
                .validateLoginLogo();

        // validate that the text "Swag Labs" was presented in the class element "login_logo"
        Assertions.assertEquals("Swag Labs", swagLabs);

        String products = new LoginPage(driver)
                .register("visual_user", "secret_sauce")
                .validateProducts();

        // validate tha the text "Products" was presented in the class element "span"
        Assertions.assertEquals("Products", products);

    }

    @Test
    @DisplayName("unsuccessful sign up")
    public void testSignUpFail() {

        new LoginPage(driver)
                .accessPage()
                .register("test", "test");

        String backgroundColor = new LoginPage(driver)
                .validateErrorMessage();

        // validate the background color of the login error message
        Assertions.assertEquals("rgba(226, 35, 26, 1)", backgroundColor);

        String loginColor = new LoginPage(driver)
                .validateLoginButton();

        // validate the background color of the login button
        Assertions.assertEquals("rgba(61, 220, 145, 1)", loginColor);

        String swagLabs = new LoginPage(driver)
                .accessPage()
                .validateLoginLogo();

        // validate that the text "Swag Labs" was presented in the class element "login_logo"
        Assertions.assertEquals("Swag Labs", swagLabs);

    }

    @Test
    @DisplayName("add to cart and complete checkout")
    public void testAddToCartAndDoCheckout() {

        String sauceLabsBackpack = new LoginPage(driver)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addBackpackToCart()
                .goToCart()
                .validateItemName();

        // validate that the product name is correct
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsBackpack);

        String continueColor = new CartPage(driver)
                .goToCheckoutInfo()
                .checkoutInformation("Diego", "Maradona", "31000000")
                .validateContinue();

        // validate the background color of the continue button
        Assertions.assertEquals("rgba(61, 220, 145, 1)", continueColor);

        String sauceLabsCheckout = new CheckoutInfoPage(driver)
                .clickContinue()
                .validateItemName();

        // validate that the product name is correct at checkout
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsCheckout);

        String payment = new CheckoutViewPage(driver)
                .validatePaymentInfo();

        // validate the text "Payment Information:"
        Assertions.assertEquals("Payment Information:", payment);

        String shipping = new CheckoutViewPage(driver)
                .validateShippingInfo();

        // validate the text "Shipping Information:"
        Assertions.assertEquals("Shipping Information:", shipping);

        String price = new CheckoutViewPage(driver)
                .validatePriceTotal();

        // validate the text "Price Total"
        Assertions.assertEquals("Price Total", price);

        String finishColor = new CheckoutViewPage(driver)
                .validateFinish();

        // validate the background color of the finish button
        Assertions.assertEquals("rgba(61, 220, 145, 1)", finishColor);

        String complete = new CheckoutViewPage(driver)
                .clickFinish()
                .validateComplete();

        // validate the text "Checkout: Complete!"
        Assertions.assertEquals("Checkout: Complete!", complete);

        String thankYou = new CheckoutCompletePage(driver)
                .validateThankYou();

        // validate the text "Thank you for your order!"
        Assertions.assertEquals("Thank you for your order!", thankYou);

        String backHomeColor = new CheckoutCompletePage(driver)
                .validateBackHome();

        // validate the background color of the back home button
        Assertions.assertEquals("rgba(61, 220, 145, 1)", backHomeColor);

        new CheckoutCompletePage(driver)
                .clickBackHome();

    }

    @Test
    @DisplayName("add to cart and remove by a click")
    public void testAddToCartAndRemoveByClick() {

        new LoginPage(driver)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addBackpackToCart()
                .removeBackpackToCart();

    }

    @Test
    @DisplayName("add to cart and remove by the cart")
    public void testAddToCartAndRemoveByCart() {

        String sauceLabsBackpack = new LoginPage(driver)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addBackpackToCart()
                .goToCart()
                .validateItemName();

        // validate that the product name is correct
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsBackpack);

        new CartPage(driver)
                .removeBackpackToCart()
                .continueShopping();

    }

    @Test
    @DisplayName("successful sign up and logout")
    public void testLoginSuccessAndLogout() {

        new LoginPage(driver)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .clickBurgerMenu()
                .clickLogout();

    }

    @Test
    @DisplayName("add to cart and reset app state")
    public void testResetAppState() {

        new LoginPage(driver)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addRedToCart()
                .addBackpackToCart()
                .addBikeToCart()
                .addBoltToCart()
                .addJacketToCart()
                .addOnesieToCart()
                .clickBurgerMenu()
                .clickReset();

    }

}
