package sauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement insertFirstName;

    @FindBy(id = "last-name")
    private WebElement insertLastName;

    @FindBy(id = "postal-code")
    private WebElement insertPostalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutInfoPage(WebDriver navegador) {
        super(navegador);
    }

    public CheckoutInfoPage checkoutInformation(String firtName, String lastName, String postalCode) {
        insertFirstName.sendKeys(firtName);
        insertLastName.sendKeys(lastName);
        insertPostalCode.sendKeys(postalCode);
        return new CheckoutInfoPage(navegador);
    }

    public CheckoutViewPage clickContinue() {
        continueButton.click();
        return new CheckoutViewPage(navegador);
    }

    public String validateContinue() {
        return continueButton.getCssValue("background-color");
    }

}

