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

        this.pageTitle = page.getByTestId("page-title");
        this.favoriteButton = page.getByTestId("nav-favorites");
        this.profileButton = page.getByTestId("nav-profile");
        this.invoicesButton = page.getByTestId("nav-invoices");
        this.messagesButton = page.getByTestId("nav-messages");
    }
}
