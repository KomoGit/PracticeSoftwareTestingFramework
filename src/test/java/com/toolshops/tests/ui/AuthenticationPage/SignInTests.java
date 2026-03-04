package com.toolshops.tests.ui.AuthenticationPage;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.toolshop.framework.pages.SignInPage;
import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignInTests extends BaseTest {
    private SignInPage signInPage;
    private final static String INVALID_EMAIL = "example@example.com";
    private final static String INVALID_PASSWORD = "12345";
    private final static String VALID_EMAIL = "";
    private final static String VALID_PASSWORD = "";

    @BeforeEach()
    void setup(){
        signInPage = new SignInPage(threadPage.get());
    }

    @Test
    @Step("checkSignInPageTitleTest")
    void checkSignInPageTitleTest(){
        signInPage.open();
        Assertions.assertThat(signInPage.getPageTitle()).contains("Login - ");
    }

    @Test
    @Step("signInWithInvalidCredentialsTest")
    void signInWithInvalidCredentialsTest(){
        signInPage
                .open()
                .login(INVALID_EMAIL, INVALID_PASSWORD);

        PlaywrightAssertions.assertThat(signInPage.errorBox).containsText(signInPage.invalidLoginErrorMessage);
    }

    @Test
    @Step("SignInWithoutFillingInputsTest")
    void SignInWithoutFillingInputsTest(){
        signInPage
                .open()
                .login("", "");

        PlaywrightAssertions.assertThat(signInPage.emailErrorBox).containsText(signInPage.emailIsRequiredErrorMessage);
        PlaywrightAssertions.assertThat(signInPage.passwordErrorBox).containsText(signInPage.passwordRequiredErrorMessage);
    }
}