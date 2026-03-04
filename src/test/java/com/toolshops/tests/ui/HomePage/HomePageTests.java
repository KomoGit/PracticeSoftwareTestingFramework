package com.toolshops.tests.ui.HomePage;

import com.toolshop.framework.pages.HomePage;
import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class HomePageTests extends BaseTest {
    private HomePage homePage;

    @BeforeEach
    void setup(){
        homePage = new HomePage(threadPage.get());
    }

    @Test
    @DisplayName("Verify Home Page Title")
    @Description("Checks if the correct title is displayed upon loading the home page")
    void checkHomePageTitleTest(){
        homePage.waitForPageLoaded();
        Assertions.assertThat(homePage.getPageTitle()).contains("Practice Software Testing");
    }

    @Test
    @DisplayName("Search for specific items")
    @Description("Verifies that searching for an item filters the product list correctly")
    void searchForItemsTest(){
        homePage.waitForPageLoaded();
        homePage.fillSearchInputAndSubmit("Pliers");

        Assertions.assertThat(homePage.getAllProductNames())
                .isNotEmpty()
                .allSatisfy(productName ->
                        Assertions.assertThat(productName).containsIgnoringCase("pliers"));
    }

    @Test
    @DisplayName("Clear search input")
    @Description("Verifies that clearing the search restores the default product list")
    void clearSearchTest(){
        homePage.waitForPageLoaded();
        int defaultProductCount = homePage.getProductCount();

        homePage.fillSearchInputAndSubmit("Pliers");
        int searchedProductCount = homePage.getProductCount();

        Assertions.assertThat(searchedProductCount)
                .isLessThan(defaultProductCount);

        homePage.clearSearchInput();

        int clearedProductCount = homePage.getProductCount();
        Assertions.assertThat(clearedProductCount)
                .isEqualTo(defaultProductCount);
    }


}