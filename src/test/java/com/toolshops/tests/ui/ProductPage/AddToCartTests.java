package com.toolshops.tests.ui.ProductPage;

import com.toolshop.framework.pages.HomePage;
import com.toolshop.framework.pages.ProductPage;
import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class AddToCartTests extends BaseTest {
    private ProductPage productPage;
    private HomePage homePage;

    private final String productName = "Bolt Cutters";
    private final String toastMessage = "Product added to shopping cart.";

    @BeforeEach
    void setup(){
        productPage = new ProductPage(threadPage.get());
        homePage = new  HomePage(threadPage.get());
    }

    @Test
    @DisplayName("Add to cart toast message test")
    @Description("Checks if adding an item to the cart is followed by toast message.")
    void addProductToCartTest(){
        homePage
                .clickProductByName(productName)
                .clickAddToCartButton()
                .waitForToastAppear();

        Assertions.assertThat(productPage.getToastText()).contains(toastMessage);
    }


}