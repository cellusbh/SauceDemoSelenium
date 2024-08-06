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

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage removeBackpackToCart() {
        removeBackpack.click();
        return new CartPage(driver);
    }

    public HomePage continueShopping() {
        backShopping.click();
        return new HomePage(driver);
    }

    public CheckoutInfoPage goToCheckoutInfo() {
        checkoutButton.click();
        return new CheckoutInfoPage(driver);
    }

    public String validateItemName() {
        return itemName.getText();
    }

}
