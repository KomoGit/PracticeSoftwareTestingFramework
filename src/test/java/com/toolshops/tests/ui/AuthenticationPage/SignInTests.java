package com.toolshops.tests.ui.AuthenticationPage;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.toolshop.framework.models.UserModel;
import com.toolshop.framework.pages.AccountPage;
import com.toolshop.framework.pages.SignInPage;
import com.toolshop.framework.utils.KeyVaultReader;
import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SignInTests extends BaseTest {
    private SignInPage signInPage;
    private AccountPage accountPage;
    private KeyVaultReader keyVaultReader;

    @BeforeEach()
    void setup(){
        signInPage = new SignInPage(threadPage.get());
        accountPage = new AccountPage(threadPage.get());
        keyVaultReader = new KeyVaultReader(config.keyVaultUrl);
    }

    @Test
    @DisplayName("Verify Home Page Title")
    @Description("Checks if the correct title is displayed upon loading the page")
    void checkSignInPageTitleTest(){
        signInPage.open();
        Assertions.assertThat(signInPage.getPageTitle()).contains("Login - ");
    }

    @Test
    @DisplayName("Verify error message when fields are not filled")
    @Description("If the fields are not filled, error messages must come up")
    void signInWithoutFillingInputsTest(){
        signInPage
                .open()
                .login("", "");

        PlaywrightAssertions.assertThat(signInPage.emailErrorBox).containsText(signInPage.emailIsRequiredErrorMessage);
        PlaywrightAssertions.assertThat(signInPage.passwordErrorBox).containsText(signInPage.passwordRequiredErrorMessage);
    }

    @Test
    @DisplayName("Verify user cannot sign in with invalid credentials")
    @Description("Checks if the error message will be shown upon using invalid credentials")
    void signInWithInvalidCredentialsTest(){
        UserModel userModel = keyVaultReader.getSecretAs("invalid-user", UserModel.class);
        signInPage
                .open()
                .login(userModel.Email, userModel.Password);

        PlaywrightAssertions.assertThat(signInPage.errorBox).containsText(signInPage.invalidLoginErrorMessage);
    }

    @Test
    @DisplayName("Sign in as customer")
    @Description("Signing in as a regular customer account ")
    void signInWithUserAccountTest() {
        UserModel userModel = keyVaultReader.getSecretAs("jane-doe", UserModel.class);
        signInPage
                .open()
                .login(userModel.Email, userModel.Password); 

        PlaywrightAssertions.assertThat(accountPage.pageTitle).containsText(accountPage.pageTitleText);
    }
}