package com.toolshop.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;

public class HomePage extends BasePage {

    public final Locator searchInput;
    public final Locator searchReset;
    public final Locator searchSubmit;
    public final Locator sortBox;
    public final Locator product;
    public final Locator productPrices;

    public HomePage(Page page) {
        super(page);

        this.searchInput = page.getByTestId("search-query");
        this.searchReset = page.getByTestId("search-reset");
        this.searchSubmit = page.getByTestId("search-submit");
        this.sortBox = page.getByTestId("sort");
        this.product = page.getByTestId("product-name");
        this.productPrices = page.getByTestId("product-price");
    }

    public HomePage fillSearchInputAndSubmit(String text){
        searchInput.fill(text);
        page.waitForResponse(
                response -> response.url().contains("/products/search"),
                searchSubmit::click
        );
        return this;
    }

    public HomePage clearSearchInput(){
        page.waitForResponse(
          response -> response.url().contains("/products"),
                searchReset::click
        );
        return this;
    }

    public HomePage clickSortOptionByText(String text){
        sortBox.click();
        sortBox.selectOption(text);
        return this;
    }

    public List<String> getAllProductNames(){
        product.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
        return product.allTextContents();
    }

    public List<Double> getAllProductPrices(){
        productPrices.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
        return productPrices
                .allInnerTexts()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$","")))
                .toList();
    }

    public int getProductCount(){
        return product.count();
    }
}