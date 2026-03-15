package com.toolshop.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * @author Elnur Abaslı
 */
public class ContactPage extends BasePage{

    public Locator pageTitle;
    public Locator firstNameInput;
    public Locator lastNameInput;
    public Locator emailInput;
    public final String pageTitleText = "Contact";

    public ContactPage(Page page) {
        super(page);

        this.pageTitle = page.locator("//h3[text()='Contact']");
        this.firstNameInput = page.getByTestId("first_name");
        this.lastNameInput = page.getByTestId("last_name");
        this.emailInput = page.getByTestId("email");
    }

    public ContactPage open() {
        String url = "/contact";
        page.navigate(url);
        return this;
    }

}
