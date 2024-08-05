package sauceDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    @FindBy(id = "back-to-products")
    private WebElement backHome;

    @FindBy(xpath = "//span[text()='Checkout: Complete!']")
    private WebElement complete;

    @FindBy(className = "complete-header")
    private WebElement thankYou;

    public CheckoutCompletePage(WebDriver navegador) {
        super(navegador);
    }

    public HomePage clickBackHome() {
        backHome.click();
        return new HomePage(navegador);
    }

    public String validateComplete() {
        return complete.getText();
    }

    public String validateThankYou() {
        return thankYou.getText();
    }

    public String validateBackHome() {
        return backHome.getCssValue("background-color");
    }

}
