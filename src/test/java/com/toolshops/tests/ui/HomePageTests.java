package com.toolshops.tests.ui;

import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomePageTests extends BaseTest {

    @Test
    @Step("Checking the page title.")
    void shouldShowThePageTitle(){
        Assertions.assertTrue(page.title().contains("Practice Software Testing"));
    }


}