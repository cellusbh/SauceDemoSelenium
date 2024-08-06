package sauceDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpack;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBike;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addBolt;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement addJacket;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    private WebElement addOnesie;

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    private WebElement addRed;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpack;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement reset;

    @FindBy(xpath = "//span[text()='Products']")
    private WebElement products;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String validateProducts() {
        return products.getText();
    }

    public HomePage addBackpackToCart() {
        addBackpack.click();
        return new HomePage(driver);
    }

    public HomePage addBikeToCart() {
        addBike.click();
        return new HomePage(driver);
    }

    public HomePage addBoltToCart() {
        addBolt.click();
        return new HomePage(driver);
    }

    public HomePage addJacketToCart() {
        addJacket.click();
        return new HomePage(driver);
    }

    public HomePage addOnesieToCart() {
        addOnesie.click();
        return new HomePage(driver);
    }

    public HomePage addRedToCart() {
        addRed.click();
        return new HomePage(driver);
    }

    public HomePage removeBackpackToCart() {
        removeBackpack.click();
        return new HomePage(driver);
    }

    public CartPage goToCart() {
        cartLink.click();
        return new CartPage(driver);
    }

    public HomePage clickBurgerMenu() {
        burgerMenu.click();
        return new HomePage(driver);
    }

    public LoginPage clickLogout() {
        logout.click();
        return new LoginPage(driver);
    }

    public HomePage clickReset() {
        reset.click();
        return new HomePage(driver);
    }

}
