package com.toolshop.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SignInPage extends BasePage {
    private final String url = "/auth/login";

    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator failedErrorBox;
    private final Locator emailErrorBox;
    private final Locator passwordErrorBox;
    private final Locator alertDangerBox;

    public final String invalidLoginErrorMessage = "Invalid email or password";
    public final String emailIsRequiredErrorMessage = "Email is required";
    public final String passwordRequiredErrorMessage = "Password is required";

    public SignInPage(Page page) {
        super(page);

        this.emailInput = page.locator("#email");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("input[data-test='login-submit']");
        this.failedErrorBox = page.locator("//div[@class='help-block']");
        this.emailErrorBox = page.locator("#email-error");
        this.passwordErrorBox = page.locator("#password-error");
        this.alertDangerBox = page.locator(".alert-danger");
    }

    public SignInPage open() {
        page.navigate(url);
        return this;
    }

    public SignInPage login(String email, String password) {
        emailInput.fill( email);
        passwordInput.fill(password);
        loginButton.click();
        return this;
    }

    public String getErrorFailMessage(){
        return failedErrorBox.textContent().trim();
    }

    public String getEmailErrorMessage(){
        return emailErrorBox.textContent().trim();
    }

    public String getPasswordErrorMessage(){
        return passwordErrorBox.textContent().trim();
    }

    public Locator getAlertDangerBox() {
        return alertDangerBox;
    }
}