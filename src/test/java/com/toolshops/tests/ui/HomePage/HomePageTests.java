package com.toolshops.tests.ui.HomePage;

import com.toolshop.framework.pages.HomePage;
import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


class HomePageTests extends BaseTest {
    private HomePage homePage;
    private String searchInput = "pliers";

    @BeforeEach
    void setup(){
        homePage = new HomePage(threadPage.get());
    }

    @Test
    @DisplayName("Verify home page title")
    @Description("Checks if the correct title is displayed upon loading the page")
    void checkHomePageTitleTest(){
        homePage.waitForPageLoaded();
        Assertions.assertThat(homePage.getPageTitle()).contains("Practice Software Testing");
    }

    @Test
    @DisplayName("Search for specific items")
    @Description("Verifies that searching for an item filters the product list correctly")
    void searchForItemsTest(){
        homePage.waitForPageLoaded();
        homePage.fillSearchInputAndSubmit(searchInput);

        Assertions.assertThat(homePage.getAllProductNames())
                .isNotEmpty()
                .allSatisfy(productName ->
                        Assertions.assertThat(productName).containsIgnoringCase(searchInput));
    }

    @Test
    @DisplayName("Clear search input")
    @Description("Verifies that clearing the search restores the default product list")
    void clearSearchTest(){
        homePage.waitForPageLoaded();
        int defaultProductCount = homePage.getProductCount();

        homePage.fillSearchInputAndSubmit(searchInput);
        int searchedProductCount = homePage.getProductCount();

        Assertions.assertThat(searchedProductCount)
                .isLessThan(defaultProductCount);

        homePage.clearSearchInput();

        int clearedProductCount = homePage.getProductCount();
        Assertions.assertThat(clearedProductCount)
                .isEqualTo(defaultProductCount);
    }

    @Test
    @DisplayName("Prices should be correct values")
    @Description("Prices should be in correct value range")
    void allPricesShouldBeCorrectValues(){
        homePage.waitForPageLoaded();
        var productPrices = homePage.getAllProductPrices();
        Assertions.assertThat(productPrices)
                .isNotEmpty()
                .allSatisfy(price ->
                        Assertions.assertThat(price)
                                .isGreaterThan(0.0)
                                .isLessThan(1000.0));
    }

    @Test
    @DisplayName("Product names should be sorted in A-Z alphabetical order")
    @Description("Names are sorted in the descending alphabetical order from A to Z")
    void shouldSortInAlphabeticalOrder(){
        homePage
                .clickSortOptionByText("Name (A - Z)")
                .waitForPageElement(".card-img-top")
                .waitForPageLoaded();

        Assertions
                .assertThat(homePage.getAllProductNames())
                .isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER);
    }

    @Test
    @DisplayName("Product names should be sorted in Z-A alphabetical order")
    @Description("Names are sorted in the ascending alphabetical order from Z to A")
    void shouldSortInReverseAlphabeticalOrder(){
        homePage
                .clickSortOptionByText("Name (Z - A)")
                .waitForPageElement(".card-img-top")
                .waitForPageLoaded();

        Assertions
                .assertThat(homePage.getAllProductNames())
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}