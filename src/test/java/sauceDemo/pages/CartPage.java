package sauceDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpack;

    @FindBy(id = "continue-shopping")
    private WebElement backShopping;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "inventory_item_name")
    private WebElement itemName;

    public CartPage(WebDriver navegador) {
        super(navegador);
    }

    public CartPage removeBackpackToCart() {
        removeBackpack.click();
        return new CartPage(navegador);
    }

    public HomePage continueShopping() {
        backShopping.click();
        return new HomePage(navegador);
    }

    public CheckoutInfoPage goToCheckoutInfo() {
        checkoutButton.click();
        return new CheckoutInfoPage(navegador);
    }

    public String validateItemName() {
        return itemName.getText();
    }

}
