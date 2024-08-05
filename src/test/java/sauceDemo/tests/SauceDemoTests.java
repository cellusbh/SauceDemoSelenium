package sauceDemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sauceDemo.pages.*;

import java.time.Duration;

@DisplayName("testes automatizados no saucedemo.com")
public class SauceDemoTests {

    private WebDriver navegador;

    @BeforeEach
    public void setUp() {

        // abrir o navegador
        WebDriverManager.chromedriver().setup();
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @AfterEach
    public void tearDown() {

        // fechar o navegador
        if (navegador != null) {
            navegador.quit();
        }

    }

    @Test
    @DisplayName("sign up success")
    public void testSignUpSuccess() {

        String swagLabs = new LoginPage(navegador)
                .accessPage()
                .validateLoginLogo();

        // validar que o texto "Swag Labs" foi apresentado no elemento class "login_logo"
        Assertions.assertEquals("Swag Labs", swagLabs);

        String products = new LoginPage(navegador)
                .register("visual_user", "secret_sauce")
                .validateProducts();

        // validar que o texto "Products" foi apresentado no elemento class "span"
        Assertions.assertEquals("Products", products);

    }

    @Test
    @DisplayName("sign up fail")
    public void testSignUpFail() {

        new LoginPage(navegador)
                .accessPage()
                .register("teste", "teste");

        String backgroundColor = new LoginPage(navegador)
                .validateErrorMessage();

        // validar o background color da mensagem de erro no login
        Assertions.assertEquals("rgba(226, 35, 26, 1)", backgroundColor);

        String loginColor = new LoginPage(navegador)
                .validateLoginButton();

        // validar o background color do botão de login
        Assertions.assertEquals("rgba(61, 220, 145, 1)", loginColor);

        String swagLabs = new LoginPage(navegador)
                .accessPage()
                .validateLoginLogo();

        // validar que o texto "Swag Labs" foi apresentado no elemento class "login_logo"
        Assertions.assertEquals("Swag Labs", swagLabs);

    }

    @Test
    @DisplayName("add to cart and do checkout")
    public void testAddToCartAndDoCheckout() {

        String sauceLabsBackpack = new LoginPage(navegador)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addBackpackToCart()
                .goToCart()
                .validateItemName();

        // validar se o nome do produto está correto
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsBackpack);

        String continueColor = new CartPage(navegador)
                .goToCheckoutInfo()
                .checkoutInformation("Diego", "Maradona", "31000000")
                .validateContinue();

        // validar o background color do botão de continue
        Assertions.assertEquals("rgba(61, 220, 145, 1)", continueColor);

        String sauceLabsCheckout = new CheckoutInfoPage(navegador)
                .clickContinue()
                .validateItemName();

        // validar se o nome do produto está correto no checkout
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsCheckout);

        String payment = new CheckoutViewPage(navegador)
                .validatePaymentInfo();

        // validar o texto "Payment Information:"
        Assertions.assertEquals("Payment Information:", payment);

        String shipping = new CheckoutViewPage(navegador)
                .validateShippingInfo();

        // validar o texto "Shipping Information:"
        Assertions.assertEquals("Shipping Information:", shipping);

        String price = new CheckoutViewPage(navegador)
                .validatePriceTotal();

        // validar o texto "Price Total"
        Assertions.assertEquals("Price Total", price);

        String finishColor = new CheckoutViewPage(navegador)
                .validateFinish();

        // validar o background color do botão de finish
        Assertions.assertEquals("rgba(61, 220, 145, 1)", finishColor);

        String complete = new CheckoutViewPage(navegador)
                .clickFinish()
                .validateComplete();

        // validar o texto "Checkout: Complete!"
        Assertions.assertEquals("Checkout: Complete!", complete);

        String thankYou = new CheckoutCompletePage(navegador)
                .validateThankYou();

        // validar o texto "Thank you for your order!"
        Assertions.assertEquals("Thank you for your order!", thankYou);

        String backHomeColor = new CheckoutCompletePage(navegador)
                .validateBackHome();

        // validar o background color do botão de back home
        Assertions.assertEquals("rgba(61, 220, 145, 1)", backHomeColor);

        new CheckoutCompletePage(navegador)
                .clickBackHome();

    }

    @Test
    @DisplayName("add to cart and remove by click")
    public void testAddToCartAndRemoveByClick() {

        new LoginPage(navegador)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addBackpackToCart()
                .removeBackpackToCart();

    }

    @Test
    @DisplayName("add to cart and remove by cart")
    public void testAddToCartAndRemoveByCart() {

        String sauceLabsBackpack = new LoginPage(navegador)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addBackpackToCart()
                .goToCart()
                .validateItemName();

        // validar que o nome do produto está correto
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsBackpack);

        new CartPage(navegador)
                .removeBackpackToCart()
                .continueShopping();

    }

    @Test
    @DisplayName("sign up success and logout")
    public void testLoginSuccessAndLogout() {

        new LoginPage(navegador)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .clickBurgerMenu()
                .clickLogout();

    }

    @Test
    @DisplayName("reset app state")
    public void testResetAppState() {

        new LoginPage(navegador)
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
