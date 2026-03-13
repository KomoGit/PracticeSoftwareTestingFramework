package com.toolshop.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AccountPage extends BasePage{

    public final Locator pageTitle;
    public final Locator favoriteButton;
    public final Locator profileButton;
    public final Locator invoicesButton;
    public final Locator messagesButton;

    public final String pageTitleText = "My account";


    public AccountPage(Page page) {
        super(page);

        this.pageTitle = page.locator("//h1[@data-test='page-title']");
        this.favoriteButton = page.locator("//a[@data-test='nav-favorites']");
        this.profileButton = page.locator("//a[@data-test='nav-profile']");
        this.invoicesButton = page.locator("//a[@data-test='nav-invoices']");
        this.messagesButton = page.locator("//a[@data-test='nav-messages']");
    }
}
