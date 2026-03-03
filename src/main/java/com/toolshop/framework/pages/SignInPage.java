package com.toolshop.framework.pages;

import com.microsoft.playwright.Page;

public class SignInPage extends BasePage {
    private final String url = "/auth/login";
    private final String emailInput = "id=email";
    private final String passwordInput = "id=password";
    private final String loginButton = "css=input[data-test='login-submit']";

    public SignInPage(Page page) {
        super(page);
    }

    public void login(String email, String password){
        page.fill(emailInput, email);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }

    public void open(){
        navigateTo(url);
    }
}
