package sauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutViewPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finish;

    public CheckoutViewPage(WebDriver navegador) {
        super(navegador);
    }

    public CheckoutCompletePage clickFinish() {
        finish.click();
        return new CheckoutCompletePage(navegador);
    }

}
