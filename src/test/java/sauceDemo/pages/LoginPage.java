package sauceDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement insertUsername;

    @FindBy(id = "password")
    private WebElement insertPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(css = ".error-message-container.error")
    private WebElement errorMessage;

    public LoginPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginPage accessPage() {
        navegador.get("https://www.saucedemo.com/");
        return new LoginPage(navegador);
    }

    public HomePage register(String username, String password) {
        insertUsername.sendKeys(username);
        insertPassword.sendKeys(password);
        loginButton.click();
        return new HomePage(navegador);
    }

    public String validateLoginLogo() {
        return loginLogo.getText();
    }

    public String validateErrorMessage() {
        return errorMessage.getCssValue("background-color");
    }

    public String validateLoginButton() {
        return loginButton.getCssValue("background-color");
    }

}
