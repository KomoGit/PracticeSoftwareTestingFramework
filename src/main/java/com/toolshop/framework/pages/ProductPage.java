package com.toolshop.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;



public class ProductPage extends BasePage {

    public final Locator productName;
    public final Locator productPrice;
    public final Locator productCO2Rating;
    public final Locator productDescription;
    public final Locator addToCartButton;
    public final Locator addToFavoritesButton;
    public final Locator addToCompareButton;
    private Locator productImage;
    public final Locator addToCartToast;


    public ProductPage(Page page) {
        super(page);

        this.productName = page.getByTestId("product-name");
        this.productPrice = page.getByTestId("unit-price");
        this.productCO2Rating = page.getByTestId("co2-rating-badge");
        this.productDescription = page.getByTestId("product-description");
        this.addToCartButton = page.getByTestId("add-to-cart");
        this.addToFavoritesButton = page.getByTestId("add-to-favorites");
        this.addToCompareButton = page.getByTestId("add-to-compare");
        this.addToCartToast  = page.getByRole(AriaRole.ALERT);
    }

    //product image does not have any test id, but the alt text is always same as product name.
    public Locator getProductImage() {
        this.productImage = page.getByAltText(productName.textContent());
        return this.productImage;
    }

    public ProductPage clickAddToFavoritesButton(){
        this.addToFavoritesButton.click();
        return this;
    }

    public ProductPage clickAddToCompareButton(){
        this.addToCompareButton.click();
        return this;
    }

    public ProductPage clickAddToCartButton(){
        this.addToCartButton.click();
        return this;
    }

    public ProductPage waitForToastAppear(){
        addToCartToast.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2000));
        return this;
    }

    public String getToastText(){
        return addToCartToast.innerText();
    }
}