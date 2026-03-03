package com.toolshops.tests.ui.AuthenticationPage;

import com.toolshop.framework.pages.SignInPage;
import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignInTests extends BaseTest {
    private SignInPage signInPage;
    private final static String INVALID_EMAIL = "example@example.com";
    private final static String INVALID_PASSWORD = "12345";

    @BeforeEach()
    void setup(){
        signInPage = new SignInPage(threadPage.get());
    }

    @Test
    @Step("SignInWithInvalidCredentialsTest")
    public void SignInWithInvalidCredentialsTest(){
        signInPage
                .open()
                .login(INVALID_EMAIL, INVALID_PASSWORD);

        Assertions.assertTrue(signInPage.getErrorFailMessage().contains(signInPage.invalidLoginErrorMessage));
    }

    @Test
    @Step("SignInWithoutFillingInputsTest")
    public void SignInWithoutFillingInputsTest(){
        signInPage
                .open()
                .login("", "");

        Assertions.assertTrue(signInPage.getEmailErrorMessage().contains(signInPage.emailIsRequiredErrorMessage));
        Assertions.assertTrue(signInPage.getPasswordErrorMessage().contains(signInPage.passwordRequiredErrorMessage));
    }
}