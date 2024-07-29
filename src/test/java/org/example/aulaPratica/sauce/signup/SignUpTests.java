package org.example.aulaPratica.sauce.signup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("testes automatizados da funcionalidade de sign up")
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

}
