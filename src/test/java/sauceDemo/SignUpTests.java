package sauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("testes automatizados no saucedemo.com")
public class SignUpTests {

    private WebDriver navegador;

    @BeforeEach
    public void setUp() {

        // abrir o navegador
        WebDriverManager.chromedriver().setup();
        navegador = new ChromeDriver();
        navegador.get("https://www.saucedemo.com/");
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

        // digitar o username correto
        navegador.findElement(By.id("user-name")).sendKeys("visual_user");

        // digitar o password correto
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");

        // clicar no botao de login
        navegador.findElement(By.id("login-button")).click();

        // validar que o texto "Swag Labs" foi apresentado no elemento class "app_logo"
        String swagLabs = navegador.findElement(By.className("app_logo")).getText();
        Assertions.assertEquals("Swag Labs", swagLabs);

        // validar que o texto "Products" foi apresentado no elemento class "span"
        String products = navegador.findElement(By.xpath("//span[text()='Products']")).getText();
        Assertions.assertEquals("Products", products);

    }

    @Test
    @DisplayName("sign up fail")
    public void testSignUpFail() {

        // digitar o username incorreto
        navegador.findElement(By.id("user-name")).sendKeys("teste");

        // digitar o password incorreto
        navegador.findElement(By.id("password")).sendKeys("teste");

        // clicar no botao de login
        navegador.findElement(By.id("login-button")).click();

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

        // login correto
        navegador.findElement(By.id("user-name")).sendKeys("visual_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        // adicionar item ao carrinho e ir para o carrinho
        navegador.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        navegador.findElement(By.className("shopping_cart_link")).click();

        // validar se o nome do produto está correto
        String sauceLabsBackpack = navegador.findElement(By.className("inventory_item_name")).getText();
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsBackpack);

        // clicar no botão de checkout
        navegador.findElement(By.id("checkout")).click();

        // digitar os dados necessários
        navegador.findElement(By.id("first-name")).sendKeys("Diego");
        navegador.findElement(By.id("last-name")).sendKeys("Maradona");
        navegador.findElement(By.id("postal-code")).sendKeys("31000000");

        // validar o background color do botão de continue
        WebElement btnContinue = navegador.findElement(By.id("continue"));
        String continueColor = btnContinue.getCssValue("background-color");
        Assertions.assertEquals("rgba(61, 220, 145, 1)", continueColor);

        // clicar no botão continue
        navegador.findElement(By.id("continue")).click();

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

        // clicar no botão finish
        navegador.findElement(By.id("finish")).click();

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

        // clicar no botão back home
        navegador.findElement(By.id("back-to-products")).click();


    }

    @Test
    @DisplayName("add to cart and remove by click")
    public void testAddToCartAndRemoveByClick() {

        navegador.findElement(By.id("user-name")).sendKeys("visual_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        navegador.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        navegador.findElement(By.id("remove-sauce-labs-backpack")).click();

    }

    @Test
    @DisplayName("add to cart and remove by cart")
    public void testAddToCartAndRemoveByCart() {

        navegador.findElement(By.id("user-name")).sendKeys("visual_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        navegador.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        navegador.findElement(By.className("shopping_cart_link")).click();

        // validar que o nome do produto está correto
        String sauceLabsBackpack = navegador.findElement(By.className("inventory_item_name")).getText();
        Assertions.assertEquals("Sauce Labs Backpack", sauceLabsBackpack);

        navegador.findElement(By.id("remove-sauce-labs-backpack")).click();

    }

    @Test
    @DisplayName("login success and logout")
    public void testLoginSuccessAndLogout() {

        navegador.findElement(By.id("user-name")).sendKeys("visual_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        navegador.findElement(By.id("react-burger-menu-btn")).click();
        navegador.findElement(By.id("logout_sidebar_link")).click();

    }

    @Test
    @DisplayName("reset app state")
    public void testResetAppState() {

        navegador.findElement(By.id("user-name")).sendKeys("visual_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        navegador.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        navegador.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        navegador.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        navegador.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        navegador.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        navegador.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();

        navegador.findElement(By.id("react-burger-menu-btn")).click();
        navegador.findElement(By.id("reset_sidebar_link")).click();

    }


}
