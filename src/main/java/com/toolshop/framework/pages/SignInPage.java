package com.toolshop.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SignInPage extends BasePage {
    private final String url = "/auth/login";

    public final Locator emailInput;
    public final Locator passwordInput;
    public final Locator loginButton;
    public final Locator errorBox;
    public final Locator emailErrorBox;
    public final Locator passwordErrorBox;
    public final Locator alertDangerBox;

    public final String invalidLoginErrorMessage = "Invalid email or password";
    public final String emailIsRequiredErrorMessage = "Email is required";
    public final String passwordRequiredErrorMessage = "Password is required";

    public SignInPage(Page page) {
        super(page);

        this.emailInput = page.getByTestId("email");
        this.passwordInput = page.getByTestId("password");
        this.loginButton = page.getByTestId("login-submit");
        this.errorBox = page.locator("//div[@class='help-block']");
        this.emailErrorBox = page.getByTestId("email-error");
        this.passwordErrorBox = page.getByTestId("password-error");
        this.alertDangerBox = page.getByTestId(".alert-danger");
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
}