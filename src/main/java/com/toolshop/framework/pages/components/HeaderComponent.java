package com.toolshop.framework.pages.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class HeaderComponent {
    public final Locator cartButton;
    public final Locator cartQuantity;
    public final Locator homeButton;
    public final Locator categoriesButton;
    public final Locator contactButton;
    public final Locator signInButton;
    public final Locator languageButton;

    public HeaderComponent(Page page) {
        this.cartButton = page.getByTestId("nav-cart");
        this.cartQuantity = page.getByTestId("cart-quantity");
        this.homeButton = page.getByTestId("nav-home");
        this.categoriesButton = page.getByTestId("nav-categories");
        this.contactButton = page.getByTestId("nav-contact");
        this.signInButton = page.getByTestId("nav-sign-in");
        this.languageButton = page.getByTestId("language-select");
    }

    public void clickSortOptionByText(String text){
        categoriesButton.click();
        categoriesButton.selectOption(text);
    }

    public String getCartQuantity(){
        return cartQuantity.textContent();
    }

    public void selectCategory(String category){
        categoriesButton.click();
        categoriesButton.selectOption(category);
    }
}