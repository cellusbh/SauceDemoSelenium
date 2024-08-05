package sauceDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutViewPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finish;

    @FindBy(className = "inventory_item_name")
    private WebElement itemName;

    @FindBy(xpath = "//div[text()='Payment Information:']")
    private WebElement paymentInfo;

    @FindBy(xpath = "//div[text()='Shipping Information:']")
    private WebElement shippingInfo;

    @FindBy(xpath = "//div[text()='Price Total']")
    private WebElement priceTotal;

    public CheckoutViewPage(WebDriver navegador) {
        super(navegador);
    }

    public CheckoutCompletePage clickFinish() {
        finish.click();
        return new CheckoutCompletePage(navegador);
    }

    public String validateItemName() {
        return itemName.getText();
    }

    public String validatePaymentInfo() {
        return paymentInfo.getText();
    }

    public String validateShippingInfo() {
        return shippingInfo.getText();
    }

    public String validatePriceTotal() {
        return priceTotal.getText();
    }

    public String validateFinish() {
        return finish.getCssValue("background-color");
    }

}
