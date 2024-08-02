package sauceDemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sauceDemo.pages.*;

import java.time.Duration;

@DisplayName("testes automatizados no saucedemo.com")
public class Tests {

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

        new LoginPage(navegador)
                .accessPage();

        // validar que o texto "Swag Labs" foi apresentado no elemento class "app_logo"
        String swagLabs = navegador.findElement(By.className("login_logo")).getText();
        Assertions.assertEquals("Swag Labs", swagLabs);

        new LoginPage(navegador)
                .register("visual_user", "secret_sauce");

        // validar que o texto "Products" foi apresentado no elemento class "span"
        String products = navegador.findElement(By.xpath("//span[text()='Products']")).getText();
        Assertions.assertEquals("Products", products);

    }

    @Test
    @DisplayName("sign up fail")
    public void testSignUpFail() {

        new LoginPage(navegador)
                .accessPage()
                .register("teste", "teste");

        // validar o background color da mensagem de erro no login
        WebElement element = navegador.findElement(By.cssSelector(".error-message-container.error"));
        String backgroundColor = element.getCssValue("background-color");
        Assertions.assertEquals("rgba(226, 35, 26, 1)", backgroundColor);

        // validar o background color do botão de login
        WebElement login = navegador.findElement(By.id("login-button"));
        String loginColor = login.getCssValue("background-color");
        Assertions.assertEquals("rgba(61, 220, 145, 1)", loginColor);

        // validar que o texto "Swag Labs" foi apresentado no elemento class "login_logo"
        String swagLabs = navegador.findElement(By.className("login_logo")).getText();
        Assertions.assertEquals("Swag Labs", swagLabs);

    }

    @Test
    @DisplayName("add to cart and do checkout")
    public void testAddToCartAndDoCheckout() {

        new LoginPage(navegador)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addBackpackToCart()
                .goToCart();

        // validar se o nome do produto está correto
        String sauceLabsBackpack = navegador.findElement(By.className("inventory_item_name")).getText();
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsBackpack);

        new CartPage(navegador)
                .goToCheckoutInfo()
                .checkoutInformation("Diego", "Maradona", "31000000");

        // validar o background color do botão de continue
        WebElement btnContinue = navegador.findElement(By.id("continue"));
        String continueColor = btnContinue.getCssValue("background-color");
        Assertions.assertEquals("rgba(61, 220, 145, 1)", continueColor);

        new CheckoutInfoPage(navegador)
                .clickContinue();

        // validar se o nome do produto está correto no checkout
        String sauceLabsCheckout = navegador.findElement(By.className("inventory_item_name")).getText();
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsCheckout);

        // validar o texto "Payment Information:"
        String payment = navegador.findElement(By.xpath("//div[text()='Payment Information:']")).getText();
        Assertions.assertEquals("Payment Information:", payment);

        // validar o texto "Shipping Information:"
        String shipping = navegador.findElement(By.xpath("//div[text()='Shipping Information:']")).getText();
        Assertions.assertEquals("Shipping Information:", shipping);

        // validar o texto "Price Total"
        String price = navegador.findElement(By.xpath("//div[text()='Price Total']")).getText();
        Assertions.assertEquals("Price Total", price);

        // validar o background color do botão de finish
        WebElement btnFinish = navegador.findElement(By.id("finish"));
        String finishColor = btnFinish.getCssValue("background-color");
        Assertions.assertEquals("rgba(61, 220, 145, 1)", finishColor);

        new CheckoutViewPage(navegador)
                .clickFinish();

        // validar o texto "Checkout: Complete!"
        String complete = navegador.findElement(By.xpath("//span[text()='Checkout: Complete!']")).getText();
        Assertions.assertEquals("Checkout: Complete!", complete);

        // validar o texto "Thank you for your order!"
        String thankYou = navegador.findElement(By.className("complete-header")).getText();
        Assertions.assertEquals("Thank you for your order!", thankYou);

        // validar o background color do botão de back home
        WebElement backHome = navegador.findElement(By.id("back-to-products"));
        String backHomeColor = backHome.getCssValue("background-color");
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

        new LoginPage(navegador)
                .accessPage()
                .register("visual_user", "secret_sauce")
                .addBackpackToCart()
                .goToCart();

        // validar que o nome do produto está correto
        String sauceLabsBackpack = navegador.findElement(By.className("inventory_item_name")).getText();
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsBackpack);

        new CartPage(navegador)
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
