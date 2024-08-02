package sauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    @FindBy(id = "back-to-products")
    private WebElement backHome;

    public CheckoutCompletePage(WebDriver navegador) {
        super(navegador);
    }

    public HomePage clickBackHome() {
        backHome.click();
        return new HomePage(navegador);
    }

}
